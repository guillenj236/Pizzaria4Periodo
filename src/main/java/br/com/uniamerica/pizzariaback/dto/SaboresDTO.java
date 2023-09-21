package br.com.uniamerica.pizzariaback.dto;

import lombok.Data;

@Data
public class SaboresDTO extends AbstractDTO{

    private String saborPizza;

    public SaboresDTO(){

    }

    public SaboresDTO(String saborPizza) {
        this.saborPizza = saborPizza;
    }
}
