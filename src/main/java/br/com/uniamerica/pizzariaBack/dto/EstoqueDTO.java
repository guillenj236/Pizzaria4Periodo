package br.com.uniamerica.pizzariaBack.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EstoqueDTO extends AbstractDTO {

    private float precoProdutos ;


    private String nomeProduto;

    public EstoqueDTO(float precoProdutos, String nomeProduto) {
        this.precoProdutos = precoProdutos;
        this.nomeProduto = nomeProduto;
    }
}
