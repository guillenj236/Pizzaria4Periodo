package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRep extends JpaRepository<Usuario, Long> {

    Usuario findByNomeUsuario(String nome);
}
