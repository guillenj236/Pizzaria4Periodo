package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface PedidoRep extends JpaRepository <Pedido, Long> {

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.dataPedido = :data")
    Long PedidosPorData(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.pagamentoCartao = true AND p.dataPedido = :data")
    Long TotalPedidosCartao(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.pagamentoDinheiro = true AND p.dataPedido = :data")
    Long TotalPedidosDinheiro(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.delivery = true AND p.dataPedido = :data")
    Long PedidosDelivery(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.delivery = false AND p.dataPedido = :data")
    Long TotalPedidosBalcao(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status = 'CANCELADO' AND p.dataPedido = :data")
    Long TotalCancelados(@Param("data") LocalDate data);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status = 'ENTREGUE' AND p.dataPedido = :data")
    Long TotalPagos(@Param("data") LocalDate data);

}
