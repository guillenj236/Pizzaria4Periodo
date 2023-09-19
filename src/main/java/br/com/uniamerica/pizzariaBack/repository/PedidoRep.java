package br.com.uniamerica.pizzariaBack.repository;

import br.com.uniamerica.pizzariaBack.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface PedidoRep extends JpaRepository <Pedido, Long> {

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data")
    Long PedidosPorData(@Param("data") LocalDate data);
}
