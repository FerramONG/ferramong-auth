package ferramong.auth.dtos;

import lombok.Data;

@Data
public class SignUpDTO {

    private String cpf;
    private String password;
    private String secretQuestion;
    private String secretAnswer;    
}
