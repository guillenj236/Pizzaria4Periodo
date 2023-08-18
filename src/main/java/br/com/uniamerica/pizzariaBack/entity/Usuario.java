package br.com.uniamerica.pizzariaBack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "usuarios", schema = "public")
public class Usuario extends AbstractEntity{

    @Getter @Setter
    @Column (name = "nomeUsuario", length = 50, unique = true)
    private String nomeUsuario;

    @Getter @Setter
    @OneToOne (fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_login",
        uniqueConstraints =@UniqueConstraint(
                    columnNames = {
                            "usuario_id",
                            "login_id"
                    }
        ),
            joinColumns = @JoinColumn(
                name = "usuario_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "login_id"
            )
    )
    private Login login;

    @Getter @Setter
    @Column (name = "CPF", length = 18, unique = true)
    private String CPF;
}
