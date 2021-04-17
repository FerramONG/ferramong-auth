package ferramong.auth.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDTO {

    private String cpf;
    private String name;
    private String password;
    private String secretQuestion;
    private String secretAnswer;    
}
