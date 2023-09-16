package br.com.uniamerica.pizzariaBack.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaboresDTO extends AbstractDTO{

    private String saborPizza;
}
