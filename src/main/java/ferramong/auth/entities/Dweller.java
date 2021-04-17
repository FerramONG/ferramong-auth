package ferramong.auth.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * Entity
 *    Sera mapeada para uma tabela do banco de dados
 */

@Entity
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dweller_id")
    private List<Token> tokens;
}
