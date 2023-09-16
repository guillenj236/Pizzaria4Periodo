package br.com.uniamerica.pizzariaBack.dto;
import br.com.uniamerica.pizzariaBack.entity.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginDTO extends AbstractDTO {

    private String nomeLogin;

    private String senhaLogin;
    private Usuario usuario;

}
