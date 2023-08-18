package br.com.uniamerica.pizzariaBack.entity;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table (name = "pedidos", schema = "public")
public class Pedido extends AbstractEntity{

    @Getter @Setter
    @Column(name = "entrega")
    private boolean entrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Getter @Setter
    @ManyToOne
    private Pizza pizza;

    @Getter @Setter
    @ManyToOne
    private Produtos produtos;

    @Getter @Setter
    @Column (name = "pedido_preco")
    private float pedido_preco;
    @Getter @Setter
    @Column (name = "pagamentoCartao")
    private boolean pagamentoCartao;
    @Getter @Setter
    @OneToOne
    private Funcionario funcionario;

    @Getter @Setter
    @Column (name = "observacao", length = 200)
    private String observacao;


    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "usuario",nullable = false)
    private Usuario usuario;

    @Getter @Setter
    @Column (name = "dataPedido")
    private LocalDateTime dataPedido;


}
