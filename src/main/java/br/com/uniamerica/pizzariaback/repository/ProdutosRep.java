package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRep extends JpaRepository<Produtos,Long> {
}
