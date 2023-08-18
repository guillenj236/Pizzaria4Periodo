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

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
