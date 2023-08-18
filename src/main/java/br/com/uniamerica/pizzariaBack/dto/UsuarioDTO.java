package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.Login;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioDTO extends AbstractDTO{

    private String nomeUsuario;

    private Login login;

    private String CPF;
}
