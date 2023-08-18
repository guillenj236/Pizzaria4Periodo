package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRep extends JpaRepository <Pedido, Long> {
}
