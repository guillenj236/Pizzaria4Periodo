package br.com.uniamerica.pizzariaback.dto;
import lombok.Data;

@Data
public class FuncionarioDTO extends AbstractDTO {

    private String nomeFunc;

    public FuncionarioDTO(){}
    public FuncionarioDTO(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }
}
