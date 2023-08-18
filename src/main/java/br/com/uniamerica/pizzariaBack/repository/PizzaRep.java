package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRep extends JpaRepository<Pizza, Long> {
}
