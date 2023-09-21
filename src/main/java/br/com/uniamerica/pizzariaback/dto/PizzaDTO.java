package br.com.uniamerica.pizzariaback.dto;
import br.com.uniamerica.pizzariaback.entity.Sabores;
import br.com.uniamerica.pizzariaback.entity.Tamanho;
import lombok.Data;
import java.util.List;

@Data
public class PizzaDTO extends AbstractDTO{

    private List<Sabores> sabores;

    private float precoPizza;

    private int quantidadePizza;

    private Tamanho tamanho;

    public  PizzaDTO(){

    }

    public PizzaDTO(List<Sabores> sabores, float precoPizza, int quantidadePizza, Tamanho tamanho) {
        this.sabores = sabores;
        this.precoPizza = precoPizza;
        this.quantidadePizza = quantidadePizza;
        this.tamanho = tamanho;
    }
}
