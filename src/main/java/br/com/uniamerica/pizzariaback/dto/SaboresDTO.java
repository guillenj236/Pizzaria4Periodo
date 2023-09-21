package br.com.uniamerica.pizzariaback.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaboresDTO extends AbstractDTO{

    private String saborPizza;

    public SaboresDTO(String saborPizza) {
        this.saborPizza = saborPizza;
    }
}
