package br.com.uniamerica.pizzariaBack.entity;
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
    @Getter @Setter
    @Column(name = "status")
    private Status status;

    @Getter @Setter
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id")
    private List<Pizza> pizzas;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos")
    private List<Produtos> produtos;

    @Getter @Setter
    @Column (name = "pedido_preco")
    private float pedido_preco;
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
    private LocalDateTime dataPedido;

    @Getter @Setter
    @Column(name = "delivery",nullable = false)
    private boolean delivery;


    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

}
