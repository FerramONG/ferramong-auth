package ferramong.auth.dtos;

import lombok.Data;

@Data
public class LoginDTO {
    private String cpf;
    private String password;
}
