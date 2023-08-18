package br.com.uniamerica.pizzariaBack.dto;

import br.com.uniamerica.pizzariaBack.entity.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class PedidoDTO extends AbstractDTO{

    private boolean entrega;

    private Status status;

    private Pizza pizza;

    private Produtos produtos;

    private float pedido_preco;

    private boolean pagamentoCartao;

    private Funcionario funcionario;

    private String observacao;

    private Usuario usuario;

    private LocalDateTime dataPedido;
}
