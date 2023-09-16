package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRep extends JpaRepository<Login, Long> {

    Login findByNomeLogin(String nomeLogin);
}
