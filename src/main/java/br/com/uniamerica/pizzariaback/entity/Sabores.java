package br.com.uniamerica.pizzariaback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "sabores", schema = "public")
public class Sabores extends AbstractEntity{

    @Getter @Setter
    @Column(name = "saborPizza", length = 70, unique = true)
    private String saborPizza;

    public Sabores(){

    }
    public Sabores(Long id, String saborPizza) {
        this.id = id;
        this.saborPizza = saborPizza;
    }
}
