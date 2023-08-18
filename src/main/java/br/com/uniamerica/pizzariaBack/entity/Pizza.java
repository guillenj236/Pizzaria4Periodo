package br.com.uniamerica.pizzariaBack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizza", schema = "public")
public class Pizza extends AbstractEntity{

    @ManyToMany
    private List<Sabores> sabores;

    @Getter @Setter
    @Column (name = "precoPizza", nullable = false, length = 5)
    private float precoPizza;

    @Getter @Setter
    @Column (name = "quantidadePizza", nullable = false)
    private int quantidadePizza;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho")
    private Tamanho tamanho;

}
