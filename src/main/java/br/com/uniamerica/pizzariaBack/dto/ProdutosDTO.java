package br.com.uniamerica.pizzariaBack.dto;
import br.com.uniamerica.pizzariaBack.entity.EstoqueProds;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProdutosDTO extends AbstractDTO{

    private int quantidade_prod;
    private float totalprod;
    private EstoqueProds estoqueProds;
}
