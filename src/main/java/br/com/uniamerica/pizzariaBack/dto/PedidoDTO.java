package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoDTO extends AbstractDTO{

    private boolean entrega;
    private Status status;
    private List<Pizza> pizzas;
    private List<Produtos> produtos;
    private float pedido_preco;
    private boolean pagamentoCartao;
    private boolean pagamentoDinheiro;
    private Funcionario funcionario;
    private String observacao;
    private Usuario usuario;
    private boolean delivery;
    private LocalDate dataPedido;

    public PedidoDTO(boolean entrega, Status status, List<Pizza> pizzas, List<Produtos> produtos, float pedido_preco,
                     boolean pagamentoCartao, boolean pagamentoDinheiro, Funcionario funcionario, String observacao,
                     Usuario usuario, boolean delivery, LocalDate dataPedido) {
        this.entrega = entrega;
        this.status = status;
        this.pizzas = pizzas;
        this.produtos = produtos;
        this.pedido_preco = pedido_preco;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.funcionario = funcionario;
        this.observacao = observacao;
        this.usuario = usuario;
        this.delivery = delivery;
        this.dataPedido = dataPedido;
    }
}
