package br.com.uniamerica.pizzariaback.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table (name = "pedidos", schema = "public")
public class Pedido extends AbstractEntity{

    @Getter @Setter
    @Column(name = "entrega")
    private boolean entrega;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "status")
    private Status status;

    @Getter @Setter
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private List<Pizza> pizzas;

    @Getter @Setter
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "produtos")
    private List<Produtos> produtos;

    @Getter @Setter
    @Column (name = "pedidopreco")
    private float pedidopreco;
    @Getter @Setter
    @Column (name = "pagamentoCartao")
    private boolean pagamentoCartao;

    @Getter @Setter
    @Column (name = "pagamentoDinheiro")
    private boolean pagamentoDinheiro;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario")
    private Funcionario funcionario;
    @Getter @Setter
    @Column (name = "observacao", length = 200)
    private String observacao;


    @Getter @Setter
    @Column(name = "dtCadastro")
    private LocalDateTime cadastro;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario",nullable = false)
    private Usuario usuario;

    @Getter @Setter
    @Column (name = "dataPedido")
    private LocalDate dataPedido;

    @Getter @Setter
    @Column(name = "delivery")
    private boolean delivery;

    public Pedido(){

    }
    @SuppressWarnings("java:S107")
    public Pedido(Long id,boolean entrega, Status status, List<Pizza> pizzas, List<Produtos> produtos, float pedidopreco, boolean pagamentoCartao, boolean pagamentoDinheiro, Funcionario funcionario, String observacao, LocalDateTime cadastro, Usuario usuario, LocalDate dataPedido, boolean delivery) {
       this.id = id;
        this.entrega = entrega;
        this.status = status;
        this.pizzas = pizzas;
        this.produtos = produtos;
        this.pedidopreco = pedidopreco;
        this.pagamentoCartao = pagamentoCartao;
        this.pagamentoDinheiro = pagamentoDinheiro;
        this.funcionario = funcionario;
        this.observacao = observacao;
        this.cadastro = cadastro;
        this.usuario = usuario;
        this.dataPedido = dataPedido;
        this.delivery = delivery;
    }

    @PrePersist
    private void prePersist(){
        this.dataPedido = LocalDate.now ();
    }

}
