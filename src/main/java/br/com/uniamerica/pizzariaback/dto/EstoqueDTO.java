package br.com.uniamerica.pizzariaback.dto;
import lombok.Data;
@Data
public class EstoqueDTO extends AbstractDTO {

    private float precoProdutos ;
    private String nomeProduto;
    public EstoqueDTO(){

    }
    public EstoqueDTO(float precoProdutos, String nomeProduto) {
        this.precoProdutos = precoProdutos;
        this.nomeProduto = nomeProduto;
    }
}
