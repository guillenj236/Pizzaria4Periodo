package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.Endereco;
import br.com.uniamerica.pizzariaBack.entity.Login;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UsuarioDTO extends AbstractDTO{

    private String nomeUsuario;

    private String telefone;

    private List<Endereco> enderecos;

}
