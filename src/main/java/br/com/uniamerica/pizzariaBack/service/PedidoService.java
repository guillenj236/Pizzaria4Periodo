package br.com.uniamerica.pizzariaBack.service;
import br.com.uniamerica.pizzariaBack.dto.PedidoDTO;
import br.com.uniamerica.pizzariaBack.entity.*;
import br.com.uniamerica.pizzariaBack.repository.PedidoRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRep pedidoRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraPedido(final PedidoDTO pedidoDTO){
        float total = 0;
        var pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDTO,pedido);

        if (pedido.isPagamentoCartao()){
            pedido.setPagamentoDinheiro(false);
        }else if (pedido.isPagamentoDinheiro()){
            pedido.setPagamentoCartao(false);
        }

        if (pedido.getPizzas() != null && !pedido.getPizzas().isEmpty()) {
            for (Pizza pizza : pedido.getPizzas()) {
                total += pizza.getPrecoPizza();
                System.out.println("Pizza ID: " + pizza.getId()); // Adicione este log
                System.out.println("Preço da Pizza: " + pizza.getPrecoPizza()); // Adicione este log
                System.out.println("Total parcial: " + total);
            }
        }

        /*
        if (!pedido.getProdutos().isEmpty()){
            for (Produtos produtos: pedido.getProdutos()){
                total += produtos.getQuantidade_prod();
            }
        }
         */

        System.out.println("Total antes: " + total);

        pedido.setPedido_preco(total);

        System.out.println("Total depois: " + total);


        this.pedidoRep.save(pedido);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPedido(PedidoDTO pedidoDTO){

        Pedido pedidoExistente = this.pedidoRep.findById(pedidoDTO.getId()).orElse(null);

        if (pedidoExistente != null) {

            BeanUtils.copyProperties(pedidoDTO, pedidoExistente);


            this.pedidoRep.save(pedidoExistente);
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
                writer.write("Nome da rua: " + endereco.getRua() + "\n");
                writer.write("Nome do Bairro: " + endereco.getBairro() + "\n");
            }

            for (Pizza pizza : pedido.getPizzas()){
                writer.write("Tamanho da pizza: " + pizza.getTamanho() + "\n");
                for (Sabores sabores : pizza.getSabores()){
                    writer.write("Sabor da pizza: " + sabores.getSaborPizza());
                }
            }



        }catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage() + "\n");
        }
    }
}
