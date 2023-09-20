package br.com.uniamerica.pizzariaBack.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "login", schema = "public")
public class Login extends AbstractEntity {

    @Getter @Setter
    @Column (name = "nomeLogin", length = 30,nullable = false, unique = true)
    private String nomeLogin;

    @Getter @Setter
    @Column (name = "senhaLogin", length = 20, nullable = false)
    private String senhaLogin;

    @Getter @Setter
    @OneToOne (fetch = FetchType.EAGER)
    @JoinTable(name = "login_usuario",
            uniqueConstraints =@UniqueConstraint(
                    columnNames = {
                            "login_id",
                            "usuario_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "login_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "usuario_id"
            )
    )
    private Usuario usuario;

    public Login(){}

    public Login(Long id, String nomeLogin, String senhaLogin) {
        this.id = id;
        this.nomeLogin = nomeLogin;
        this.senhaLogin = senhaLogin;
    }
}
