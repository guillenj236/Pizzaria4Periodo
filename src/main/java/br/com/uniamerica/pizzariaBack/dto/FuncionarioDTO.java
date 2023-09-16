package br.com.uniamerica.pizzariaBack.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FuncionarioDTO extends AbstractDTO {

    private String nomeFunc;
}
