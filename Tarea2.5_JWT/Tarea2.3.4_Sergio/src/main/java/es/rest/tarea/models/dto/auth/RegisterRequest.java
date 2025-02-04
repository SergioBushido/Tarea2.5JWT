package es.rest.tarea.models.dto.auth;


import es.rest.tarea.models.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
//esto se utiliza en postman/swagger para registrar un usuario (serian los campos que ponemos en el json)
//esto a traves del controlador se guarda en la bbdd