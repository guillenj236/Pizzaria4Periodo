package br.com.uniamerica.pizzariaBack.repository;
import br.com.uniamerica.pizzariaBack.entity.EstoqueProds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueProdRep extends JpaRepository<EstoqueProds, Long> {
}
