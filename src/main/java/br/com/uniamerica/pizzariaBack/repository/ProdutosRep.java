package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRep extends JpaRepository<Produtos,Long> {
}
