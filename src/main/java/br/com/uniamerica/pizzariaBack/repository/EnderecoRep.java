package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRep extends JpaRepository<Endereco,Long> {
}
