package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.Endereco;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EstoqueDTO extends AbstractDTO {

    private float precoProdutos ;


    private String nomeProduto;
}
