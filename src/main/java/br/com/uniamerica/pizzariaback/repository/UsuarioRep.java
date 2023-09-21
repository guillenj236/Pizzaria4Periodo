package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRep extends JpaRepository<Usuario, Long> {

    Usuario findByNomeUsuario(String nome);
}
