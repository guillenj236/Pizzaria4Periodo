package br.com.uniamerica.pizzariaBack.dto;


import br.com.uniamerica.pizzariaBack.entity.Sabores;
import br.com.uniamerica.pizzariaBack.entity.Tamanho;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PizzaDTO extends AbstractDTO{

    private List<Sabores> sabores;

    private float precoPizza;

    private int quantidadePizza;

    private Tamanho tamanho;
}
