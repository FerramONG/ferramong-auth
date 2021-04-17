package ferramong.auth.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Token implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String token;
    
    @Column(nullable = false)
    private OffsetDateTime createdOn;
    
    @Column(nullable = false)
    private OffsetDateTime expiresOn;
}
