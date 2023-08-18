package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Sabores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaboresRep extends JpaRepository <Sabores,Long> {

    Sabores findBySaborPizza(String saborPizza);
}
