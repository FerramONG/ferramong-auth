package ferramong.auth.dtos;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String cpf;
    private String secretAnswer;
    private String newPassword;
}
