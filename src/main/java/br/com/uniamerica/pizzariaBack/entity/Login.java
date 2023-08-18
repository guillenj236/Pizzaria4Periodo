package br.com.uniamerica.pizzariaBack.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "login", schema = "public")
public class Login extends AbstractEntity {

    @Getter @Setter
    @Column (name = "nomeLogin", length = 30,nullable = false, unique = true)
    private String nomeLogin;

    @Getter @Setter
    @Column (name = "senhaLogin", length = 20, nullable = false, unique = true)
    private String senhaLogin;

}
