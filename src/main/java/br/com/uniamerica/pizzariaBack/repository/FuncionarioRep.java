package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRep extends JpaRepository <Funcionario,Long> {
}
