package ferramong.auth.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "dweller_id")
    private Token token;
}
