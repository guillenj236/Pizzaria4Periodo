package br.com.uniamerica.pizzariaback.repository;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueProdRep extends JpaRepository<EstoqueProds, Long> {

    EstoqueProds findByNomeProduto(String nomeProduto);
}
