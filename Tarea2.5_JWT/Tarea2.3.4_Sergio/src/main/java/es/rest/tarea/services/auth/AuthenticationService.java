package es.rest.tarea.services.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.rest.tarea.models.auth.Token;
import es.rest.tarea.models.auth.TokenType;
import es.rest.tarea.models.auth.User;
import es.rest.tarea.models.dto.auth.AuthenticationRequest;
import es.rest.tarea.models.dto.auth.AuthenticationResponse;
import es.rest.tarea.models.dto.auth.RegisterRequest;
import es.rest.tarea.repositories.auth.TokenRepository;
import es.rest.tarea.repositories.auth.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    //cuando creamos un usuario hay que guardar la autenticationResponse
    // con esto se guarda el usuario en la bbdd
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))//se cifra la pass
                .role(request.getRole())
                .build();
        var savedUser = userRepository.save(user);//aqui es cuando se guarda
        var jwtToken = jwtService.generateToken(user);//aqui se genera el token
        var refreshToken = jwtService.generateRefreshToken(user);//el de refresco
        saveUserToken(savedUser, jwtToken);//y se almacena

        //respuesta con token generado
        return AuthenticationResponse.builder()
                .accessToken(jwtToken) 
                .refreshToken(refreshToken)
                .build();
    }

    // AUTHENTICATION con los datos del request (authReques en DTO desde postman)->cuando hacemos login
    //se comprueba si e puedes logear...esto te da un token
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchElementException("No user found with username: " + request.getUsername()));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    // SAVING TOKEN
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    // REVOKING TOKEN
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    // REFRESHING TOKEN
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.userRepository.findByUsername(username)
                    .orElseThrow();

            /* TO ALSO REVOKE REFRESH TOKEN
            var isTokenValid = tokenRepository.findByToken(refreshToken)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            */

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
