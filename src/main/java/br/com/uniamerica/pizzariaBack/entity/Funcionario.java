package br.com.uniamerica.pizzariaBack.entity;

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
}
