package br.com.uniamerica.pizzariaBack.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Data
public class LoginDTO extends AbstractDTO {

    private String nomeLogin;

    private String senhaLogin;
}
