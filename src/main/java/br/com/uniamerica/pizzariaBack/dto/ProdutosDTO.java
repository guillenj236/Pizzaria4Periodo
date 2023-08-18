package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.EstoqueProds;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ProdutosDTO extends AbstractDTO{


    private int quantidade_prod;


    private List<EstoqueProds> estoqueProds;
}
