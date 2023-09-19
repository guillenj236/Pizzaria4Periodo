package br.com.uniamerica.pizzariaBack.service;
import br.com.uniamerica.pizzariaBack.dto.PedidoDTO;
import br.com.uniamerica.pizzariaBack.entity.*;
import br.com.uniamerica.pizzariaBack.repository.EstoqueProdRep;
import br.com.uniamerica.pizzariaBack.repository.PedidoRep;
import br.com.uniamerica.pizzariaBack.repository.PizzaRep;
import br.com.uniamerica.pizzariaBack.repository.ProdutosRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRep pedidoRep;
    @Autowired
    private PizzaRep pizzaRep;
    @Autowired
    private ProdutosRep produtosRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraPedido(final PedidoDTO pedidoDTO){
        float total = 0;
        float totalProdutos = 0;
        var pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDTO,pedido);

        System.out.println(pedido.getPizzas().size());

        if (pedido.isPagamentoCartao()){
            pedido.setPagamentoDinheiro(false);
        }else if (pedido.isPagamentoDinheiro()){
            pedido.setPagamentoCartao(false);
        }

        if (pedido.getPizzas() != null && !pedido.getPizzas().isEmpty()) {
            for (Pizza pizza : pedido.getPizzas()) {

                Optional<Pizza> pizzaTemp = pizzaRep.findById(pizza.getId());
                total += pizzaTemp.get().getPrecoPizza();
                System.out.println("Pizza ID: " + pizza.getId());
                System.out.println("Preço da Pizza: " + pizza.getPrecoPizza());
            }
        }

        if (pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()){
            for (Produtos produtos: pedido.getProdutos()){

                Optional<Produtos> produtoTemp = produtosRep.findById(produtos.getId());
                total += produtoTemp.get().getTotalprod();
                System.out.println(totalProdutos);
            }
        }

        pedido.setPedido_preco(total+totalProdutos);

        pedido.setStatus(Status.ATIVO);

        this.pedidoRep.save(pedido);

    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPedido(PedidoDTO pedidoDTO){

        Pedido pedidoExistente = this.pedidoRep.findById(pedidoDTO.getId()).orElse(null);

        if (pedidoExistente != null) {

            BeanUtils.copyProperties(pedidoDTO, pedidoExistente);

            if (pedidoDTO.isDelivery()){
                pedidoExistente.setStatus(Status.A_CAMINHO);
            }else {
                pedidoExistente.setStatus(Status.BALCAO);
                System.out.println("POR GENTILEZA RETIRAR NO BALCAO");
            }
            this.pedidoRep.save(pedidoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void FinalizaPedido (Pedido pedido){

        Pedido pedidoFinal = this.pedidoRep.findById(pedido.getId()).orElse(null);

        if (pedido.isEntrega()){
            pedidoFinal.setStatus(Status.ENTREGUE);
        }else {
            pedidoFinal.setStatus(Status.CANCELADO);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPedido(final Long id){

        final Pedido pedidoBanco = this.pedidoRep.findById(id).orElse(null);

        if (pedidoBanco == null || !pedidoBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o pedido informado.");
        }
        this.pedidoRep.delete(pedidoBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void comandaPedido(Pedido pedido) {
        String pasta = "C:\\Users\\falco\\Documents\\Desenvolvimento\\pizzariaBack\\ComandasPizza\\";
        String arquivo = pasta + "pedido_" + pedido.getId() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {

            writer.write("Cliente: " + pedido.getUsuario().getNomeUsuario() + "\n");
            writer.write("Telefone: " + pedido.getUsuario().getTelefone() + "\n");

            for (Endereco endereco : pedido.getUsuario().getEnderecos()){
                writer.write("Num da Casa: " + endereco.getNumeroEnd() + "\n");
                writer.write("Nome da rua: " + endereco.getRua() + "\n");
                writer.write("Nome do Bairro: " + endereco.getBairro() + "\n");
            }
            for (Pizza pizza : pedido.getPizzas()){
                writer.write("Tamanho da pizza: " + pizza.getTamanho() + "\n");
                for (Sabores sabores : pizza.getSabores()){
                    writer.write("Sabor da pizza: " + sabores.getSaborPizza() + "\n");
                }
            }
            writer.write("Observacoes: " + pedido.getObservacao());
        }catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage() + "\n");
        }
    }

    @Transactional (rollbackFor = Exception.class)
    public void comandaCozinha( Pedido pedido){
        String pasta = "C:\\Users\\falco\\Documents\\Desenvolvimento\\pizzariaBack\\ComandasPizza\\";
        String arquivo = pasta + "para_cozinha_" + "pedido_" + pedido.getId() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))){
            writer.write("Cliente: " + pedido.getUsuario().getNomeUsuario() + "\n");

            for (Pizza pizza : pedido.getPizzas()){
                writer.write("Tamanho da pizza: " + pizza.getTamanho() + "\n");
                for (Sabores sabores : pizza.getSabores()){
                    writer.write("Sabor da pizza: " + sabores.getSaborPizza() + "\n");
                }
            }
            writer.write("Observacoes: " + pedido.getObservacao());

        }catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage() + "\n");
        }
    }

    public Long getPedidosPorData(LocalDate data) {
        return pedidoRep.PedidosPorData(data);
    }
}
