package br.com.uniamerica.pizzariaBack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "endereco", schema = "public")
public class Endereco extends AbstractEntity{
    @Getter @Setter
    @Column (name = "rua", length = 30, nullable = false)
    private String rua;

    @Getter @Setter
    @Column (name = "bairro", nullable = false, length = 30)
    private String bairro;

    @Getter @Setter
    @Column (name = "numeroEnd", length = 5, nullable = false)
    private int numeroEnd;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable( name = "endereco_usuario",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "endereco_id",
                            "usuario_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "endereco_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "usuario_id"
            )
    )
    private Usuario usuario;

}
