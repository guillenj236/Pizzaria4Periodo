package br.com.uniamerica.pizzariaback.dto;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProdutosDTO extends AbstractDTO{

    private int quantidade_prod;
    private float totalprod;
    private EstoqueProds estoqueProds;

    public ProdutosDTO(int quantidade_prod, float totalprod, EstoqueProds estoqueProds) {
        this.quantidade_prod = quantidade_prod;
        this.totalprod = totalprod;
        this.estoqueProds = estoqueProds;
    }
}
