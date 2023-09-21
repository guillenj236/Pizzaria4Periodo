package br.com.uniamerica.pizzariaback.dto;
import br.com.uniamerica.pizzariaback.entity.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UsuarioDTO extends AbstractDTO{

    private String nomeUsuario;

    private String telefone;

    private List<Endereco> enderecos;

    public UsuarioDTO(){}
    public UsuarioDTO(String nomeUsuario, String telefone) {
        this.nomeUsuario = nomeUsuario;
        this.telefone = telefone;
    }
}
