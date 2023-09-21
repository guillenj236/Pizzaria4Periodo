package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRep extends JpaRepository<Pizza, Long> {
}
