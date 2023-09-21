package br.com.uniamerica.pizzariaback.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos", schema = "public")
public class Produtos extends AbstractEntity{

    @Getter @Setter
    @Column (name = "quantidadeprod", nullable = false, length = 2)
    private int quantidadeprod;

    @Getter @Setter
    @Column(name= "totalprod")
    private float totalprod;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private EstoqueProds estoqueProds;

    public Produtos(){

    }
    public Produtos(Long id,int quantidadeprod, float totalprod, EstoqueProds estoqueProds) {
        this.id = id;
        this.quantidadeprod = quantidadeprod;
        this.totalprod = totalprod;
        this.estoqueProds = estoqueProds;
    }
}
