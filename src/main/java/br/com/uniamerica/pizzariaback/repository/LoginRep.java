package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRep extends JpaRepository<Login, Long> {

    Login findByNomeLogin(String nomeLogin);
}
