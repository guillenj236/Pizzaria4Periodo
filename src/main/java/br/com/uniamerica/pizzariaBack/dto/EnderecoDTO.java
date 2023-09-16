package br.com.uniamerica.pizzariaBack.dto;
import br.com.uniamerica.pizzariaBack.entity.Usuario;
import lombok.Data;

@Data
public class EnderecoDTO extends AbstractDTO{

    private String rua;

    private String bairro;

    private int numeroEnd;

    private Usuario usuario;
}
