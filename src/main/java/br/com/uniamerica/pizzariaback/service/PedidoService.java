package br.com.uniamerica.pizzariaback.service;
import br.com.uniamerica.pizzariaback.dto.PedidoDTO;
import br.com.uniamerica.pizzariaback.entity.*;
import br.com.uniamerica.pizzariaback.repository.PedidoRep;
import br.com.uniamerica.pizzariaback.repository.PizzaRep;
import br.com.uniamerica.pizzariaback.repository.ProdutosRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        if (pedido.isPagamentoCartao()){
            pedido.setPagamentoDinheiro(false);
        }else if (pedido.isPagamentoDinheiro()){
            pedido.setPagamentoCartao(false);
        }

        if (pedido.getPizzas() != null && !pedido.getPizzas().isEmpty()) {
            for (Pizza pizza : pedido.getPizzas()) {

                Optional<Pizza> pizzaTemp = pizzaRep.findById(pizza.getId());
                total += pizzaTemp.get().getPrecoPizza();
            }
        }

        if (pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()){
            for (Produtos produtos: pedido.getProdutos()){

                Optional<Produtos> produtoTemp = produtosRep.findById(produtos.getId());
                total += produtoTemp.get().getTotalprod();
            }
        }

        pedido.setPedidopreco(total+totalProdutos);

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
            }
            this.pedidoRep.save(pedidoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void finalizaPedido (Pedido pedido){

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
            throw new RegistroNaoEncontradoException("Não foi possivel identificar o pedido informado.");
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
            for (Produtos produtos : pedido.getProdutos()){
                writer.write("Nome do produto: " + produtos.getEstoqueProds().getNomeProduto() + "\n");
                writer.write("Quantidade: " + produtos.getQuantidadeprod() + "\n");
            }
            writer.write("Observacoes: " + pedido.getObservacao());

            writer.write("Total do pedido: " + pedido.getPedidopreco());
        }catch (IOException e) {
            logger.error("Erro ao salvar o arquivo: " + e.getMessage(), e);
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
            for (Produtos produtos : pedido.getProdutos()){
                writer.write("Nome do produto: " + produtos.getEstoqueProds().getNomeProduto() + "\n");
                writer.write("Quantidade: " + produtos.getQuantidadeprod() + "\n");
            }
            writer.write("Observacoes: " + pedido.getObservacao());

        }catch (IOException e) {
            logger.error("Erro ao salvar o arquivo: " + e.getMessage(), e);
        }
    }

    public Long totalPedidosPorData(LocalDate data) {
        return pedidoRep.pedidosPorData(data);
    }
    public Long totalPagamentoCartao(LocalDate data) {
        return pedidoRep.totalPedidosCartao(data);
    }
    public Long totalPagamentoDinheiro(LocalDate data) {
        return pedidoRep.totalPedidosDinheiro(data);
    }
    public Long totalPedidosDelivery(LocalDate data) {
        return pedidoRep.pedidosDelivery(data);
    }
    public Long totalPedidosBalcao(LocalDate data) {
        return pedidoRep.totalPedidosBalcao(data);
    }
    public Long totalPagos(LocalDate data) {
        return pedidoRep.totalPagos(data);
    }
    public Long totalCancelados(LocalDate data) {
        return pedidoRep.totalCancelados(data);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);


}
