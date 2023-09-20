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

    public LoginDTO(){

    }

    public LoginDTO(String nomeLogin, String senhaLogin) {
        this.nomeLogin = nomeLogin;
        this.senhaLogin = senhaLogin;
    }
}
