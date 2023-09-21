package br.com.uniamerica.pizzariaback.dto;
import br.com.uniamerica.pizzariaback.entity.Usuario;
import lombok.Data;

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
