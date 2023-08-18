package br.com.uniamerica.pizzariaBack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "produtos", schema = "public")
public class Produtos extends AbstractEntity{


    @Getter @Setter
    @Column (name = "quantidade_prod", nullable = false, length = 2)
    private int quantidade_prod;


    @Getter @Setter
   @ManyToMany
    private List <EstoqueProds> estoqueProds;




}
