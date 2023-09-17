package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
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

    private LocalDateTime dataPedido;
}
