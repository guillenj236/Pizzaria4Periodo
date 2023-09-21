package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRep extends JpaRepository<Endereco,Long> {
}
