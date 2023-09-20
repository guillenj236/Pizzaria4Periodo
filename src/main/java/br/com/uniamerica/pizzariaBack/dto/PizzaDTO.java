package br.com.uniamerica.pizzariaBack.dto;
import br.com.uniamerica.pizzariaBack.entity.Sabores;
import br.com.uniamerica.pizzariaBack.entity.Tamanho;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PizzaDTO extends AbstractDTO{

    private List<Sabores> sabores;

    private float precoPizza;

    private int quantidadePizza;

    private Tamanho tamanho;

    public PizzaDTO(List<Sabores> sabores, float precoPizza, int quantidadePizza, Tamanho tamanho) {
        this.sabores = sabores;
        this.precoPizza = precoPizza;
        this.quantidadePizza = quantidadePizza;
        this.tamanho = tamanho;
    }
}
