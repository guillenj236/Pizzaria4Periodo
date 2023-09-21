package br.com.uniamerica.pizzariaback.dto;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProdutosDTO extends AbstractDTO{

    private int quantidadeprod;
    private float totalprod;
    private EstoqueProds estoqueProds;

    public ProdutosDTO(int quantidadeprod, float totalprod, EstoqueProds estoqueProds) {
        this.quantidadeprod = quantidadeprod;
        this.totalprod = totalprod;
        this.estoqueProds = estoqueProds;
    }
}
