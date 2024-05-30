package es.rest.tarea.services;

import es.rest.tarea.models.auth.User;
import es.rest.tarea.models.dto.UserDto;
import es.rest.tarea.repositories.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
         userRepository.deleteById(id);
    }

    public UserDto createUser(UserDto userDto) {
      User user = new User();
      user.setUsername(userDto.getUsername());
      user.setPassword(userDto.getPassword());
      user.setEmail(userDto.getEmail());
      user.setRole(userDto.getRole());
      userRepository.save(user);
      return userDto;
    }

    @Transactional
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Actualizar los campos del usuario
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        // Solo actualizar la contraseña si se proporciona una nueva
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword());
        }

        userRepository.save(user);

        return userDto;
    }


}
