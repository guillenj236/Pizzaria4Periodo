package br.com.uniamerica.pizzariaback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "funcionarios", schema = "public")
public class Funcionario extends AbstractEntity{

    @Getter @Setter
    @Column (name = "nomeFunc", length = 40)
    private String nomeFunc;

    public Funcionario(){}

    public Funcionario(Long id, String nomeFunc) {
        this.id = id;
        this.nomeFunc = nomeFunc;
    }
}
