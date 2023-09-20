package br.com.uniamerica.pizzariaBack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "pizza", schema = "public")
public class Pizza extends AbstractEntity{

    @Getter@Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_sabor",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "pizza_id",
                            "sabor_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "pizza_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "sabor_id"
            )
    )
    private List<Sabores> sabores;

    @Getter @Setter
    @Column (name = "precoPizza", nullable = false, length = 5)
    private float precoPizza;

    @Getter @Setter
    @Column (name = "quantidadePizza", nullable = false)
    private int quantidadePizza;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "tamanho")
    private Tamanho tamanho;

    public Pizza(){

    }

    public Pizza(Long id,List<Sabores> sabores, float precoPizza, int quantidadePizza, Tamanho tamanho) {
        this.id = id;
        this.sabores = sabores;
        this.precoPizza = precoPizza;
        this.quantidadePizza = quantidadePizza;
        this.tamanho = tamanho;
    }
}
