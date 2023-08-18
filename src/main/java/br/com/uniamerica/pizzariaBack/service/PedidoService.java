package br.com.uniamerica.pizzariaBack.service;

import br.com.uniamerica.pizzariaBack.dto.PedidoDTO;
import br.com.uniamerica.pizzariaBack.entity.Pedido;
import br.com.uniamerica.pizzariaBack.repository.PedidoRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRep pedidoRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraPedido(final PedidoDTO pedidoDTO){
        float total = 0;
        var pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDTO,pedido);

        if(pedido.getPizzas().size() >= 1){
            for(int i=0;i<pedido.getPizzas().size(); i++) {
                total += pedido.getPizzas().get(i).getPrecoPizza();
            }
        }

        pedido.setPedido_preco(total);


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

        if (pedidoBanco == null || pedidoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o pedido informado.");
        }
        this.pedidoRep.delete(pedidoBanco);
    }
}
