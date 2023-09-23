package br.com.uniamerica.pizzariaback.dto;

import br.com.uniamerica.pizzariaback.entity.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoDTO extends AbstractDTO{

    private boolean entrega;
    private Status status;
    private List<Pizza> pizzas;
    private List<Produtos> produtos;
    private float pedidopreco;
    private boolean pagamentoCartao;
    private boolean pagamentoDinheiro;
    private Funcionario funcionario;
    private String observacao;
    private Usuario usuario;
    private boolean delivery;
    private LocalDate dataPedido;

    public PedidoDTO(){

    }

    @SuppressWarnings("java:S107")
    public PedidoDTO(boolean entrega, Status status, List<Pizza> pizzas, List<Produtos> produtos, float pedidopreco,
                     boolean pagamentoCartao, boolean pagamentoDinheiro, Funcionario funcionario, String observacao,
                     Usuario usuario, boolean delivery, LocalDate dataPedido) {
        this.entrega = entrega;
        this.status = status;
        this.pizzas = pizzas;
        this.produtos = produtos;
        this.pedidopreco = pedidopreco;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.funcionario = funcionario;
        this.observacao = observacao;
        this.usuario = usuario;
        this.delivery = delivery;
        this.dataPedido = dataPedido;
    }
}
