package br.com.uniamerica.pizzariaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estoqueProdutos", schema = "public")
public class EstoqueProds extends AbstractEntity{

    @Getter @Setter
    @Column(name = "precoProdutos", nullable = false)
    private float precoProdutos ;

    @Getter @Setter
    @Column (name = "nomeProduto", nullable = false, length = 40, unique = true)
    private String nomeProduto;

    public EstoqueProds(){

    }
    public EstoqueProds(float precoProdutos, String nomeProduto) {
        this.precoProdutos = precoProdutos;
        this.nomeProduto = nomeProduto;
    }
}

