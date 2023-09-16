package br.com.uniamerica.pizzariaBack.entity;
import java.util.List;
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
    @Column (name = "telefone", length = 15, unique = true)
    private String telefone;
    @Getter @Setter
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Endereco> enderecos;
}
