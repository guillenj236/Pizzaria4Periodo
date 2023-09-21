package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Sabores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaboresRep extends JpaRepository <Sabores,Long> {

    Sabores findBySaborPizza(String saborPizza);
}
