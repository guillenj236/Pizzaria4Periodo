package br.com.uniamerica.pizzariaBack.dto;
import br.com.uniamerica.pizzariaBack.entity.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UsuarioDTO extends AbstractDTO{

    private String nomeUsuario;

    private String telefone;

    private List<Endereco> enderecos;

}
