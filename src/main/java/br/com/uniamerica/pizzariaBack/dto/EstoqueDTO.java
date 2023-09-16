package br.com.uniamerica.pizzariaBack.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EstoqueDTO extends AbstractDTO {

    private float precoProdutos ;


    private String nomeProduto;
}
