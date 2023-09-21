package DtoUtest;
import br.com.uniamerica.pizzariaback.dto.PedidoDTO;
import br.com.uniamerica.pizzariaback.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PedidoDtoTests {
     private final List<Pizza> pizzaList = new ArrayList<>();
     private final List<Produtos> produtosList = new ArrayList<>();
     private Usuario usuario;
     private Funcionario funcionario;

     PedidoDTO pedidoDTO = new PedidoDTO(true, Status.A_CAMINHO,pizzaList,produtosList,20,
             false,false,funcionario,"teste",usuario,false, LocalDate.now());
     PedidoDTO pedidoDTO2 = new PedidoDTO();

     @Test
    void getSetPedidoPreco(){
         pedidoDTO.setPedidopreco(10);
         Assertions.assertEquals(10,pedidoDTO.getPedidopreco());
     }
     @Test
    void getSetCanceladoPedido(){
         pedidoDTO.setStatus(Status.CANCELADO);
         Assertions.assertEquals(Status.CANCELADO,pedidoDTO.getStatus());
     }
     @Test
    void getSetAcaminhoPedido(){
         pedidoDTO.setStatus(Status.A_CAMINHO);
         Assertions.assertEquals(Status.A_CAMINHO,pedidoDTO.getStatus());
     }
     @Test
    void getSetEntregue(){
         pedidoDTO.setStatus(Status.ENTREGUE);
         Assertions.assertEquals(Status.ENTREGUE,pedidoDTO.getStatus());
     }
     @Test
    void getSetBalcao(){
         pedidoDTO.setStatus(Status.BALCAO);
         Assertions.assertEquals(Status.BALCAO,pedidoDTO.getStatus());
     }
     @Test
    void getSetAtivo(){
         pedidoDTO.setStatus(Status.ATIVO);
         Assertions.assertEquals(Status.ATIVO,pedidoDTO.getStatus());
     }
     @Test
    void getSetObservacoes(){
         pedidoDTO.setObservacao("OBS");
         Assertions.assertEquals("OBS",pedidoDTO.getObservacao());
     }
     @Test
    void getSetCartaoPedido(){
         pedidoDTO.setPagamentoCartao(true);
         Assertions.assertTrue(pedidoDTO.isPagamentoCartao());
     }
     @Test
    void getSetDinheiroPedido(){
         pedidoDTO.setPagamentoDinheiro(true);
         Assertions.assertTrue(pedidoDTO.isPagamentoDinheiro());
     }
     @Test
    void getSetDeliveryPedido(){
         pedidoDTO.setDelivery(true);
         Assertions.assertTrue(pedidoDTO.isDelivery());
     }
     @Test
    void getSetIsEntregaPedido(){
         pedidoDTO.setEntrega(true);
         Assertions.assertTrue(pedidoDTO.isEntrega());
     }
     @Test
    void construtorVazioTest(){
         PedidoDTO pedidoDTO3 = new PedidoDTO();
         Assertions.assertEquals(pedidoDTO2,pedidoDTO3);
     }
     @Test
    void idTest(){
         pedidoDTO.setId(2L);
         Assertions.assertEquals(2L,pedidoDTO.getId());
     }
     @Test
    void comparacaoTest(){
         PedidoDTO pedidoDTO2 = new PedidoDTO(true, Status.A_CAMINHO,pizzaList,produtosList,20,
                 false,false,funcionario,"teste",usuario,false, LocalDate.now());
         Assertions.assertEquals(pedidoDTO,pedidoDTO2);
     }
     @Test
    void hashCodeTest(){
         PedidoDTO pedidoDTO1 = new PedidoDTO(true, Status.A_CAMINHO,pizzaList,produtosList,20,
                 false,false,funcionario,"teste",usuario,false, LocalDate.now());
         PedidoDTO pedidoDTO2 = new PedidoDTO(true, Status.A_CAMINHO,pizzaList,produtosList,20,
                 false,false,funcionario,"teste",usuario,false, LocalDate.now());

         Assertions.assertEquals(pedidoDTO1.hashCode(),pedidoDTO2.hashCode());
     }
     @Test
    void toStringTest(){
         PedidoDTO pedidoDTO2 = new PedidoDTO(true, Status.A_CAMINHO,pizzaList,produtosList,20,
                 false,false,funcionario,"teste",usuario,false, LocalDate.now());
         Assertions.assertEquals(pedidoDTO2.toString(),pedidoDTO.toString());
     }
}
