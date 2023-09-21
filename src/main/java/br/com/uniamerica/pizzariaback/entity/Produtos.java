package br.com.uniamerica.pizzariaback.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos", schema = "public")
public class Produtos extends AbstractEntity{

    @Getter @Setter
    @Column (name = "quantidade_prod", nullable = false, length = 2)
    private int quantidade_prod;

    @Getter @Setter
    @Column(name= "totalprod")
    private float totalprod;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private EstoqueProds estoqueProds;

    public Produtos(){

    }
    public Produtos(Long id,int quantidade_prod, float totalprod, EstoqueProds estoqueProds) {
        this.id = id;
        this.quantidade_prod = quantidade_prod;
        this.totalprod = totalprod;
        this.estoqueProds = estoqueProds;
    }
}
