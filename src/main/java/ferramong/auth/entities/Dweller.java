package ferramong.auth.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*
 * Entity
 *    Sera mapeada para uma tabela do banco de dados
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dweller implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String secretQuestion;

    @Column(nullable = false)
    private String secretAnswer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private Token token;
}
