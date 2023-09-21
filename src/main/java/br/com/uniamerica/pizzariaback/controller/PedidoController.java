package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.PedidoDTO;
import br.com.uniamerica.pizzariaback.dto.RelatorioDiaDTO;
import br.com.uniamerica.pizzariaback.entity.Pedido;
import br.com.uniamerica.pizzariaback.repository.PedidoRep;
import br.com.uniamerica.pizzariaback.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRep pedidoRep;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findByIdPath(@PathVariable("id") final Long id){
        final Pedido pedido = this.pedidoRep.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Pedido>> listaPedidos(){
        return ResponseEntity.ok(this.pedidoRep.findAll());
    }

    @GetMapping ("/comanda/{id}")
    public ResponseEntity <String> findById (@PathVariable ("id") final Long id){
        try {
            Pedido pedido = pedidoRep.getReferenceById(id);
            pedidoService.comandaPedido(pedido);
            return ResponseEntity.ok("comanda gerada com sucesso");
        }catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @GetMapping ("/cozinha/{id}")
    public ResponseEntity <String> comandaDeCria (@PathVariable ("id") Long id){
        try {
            Pedido pedido = pedidoRep.getReferenceById(id);
            pedidoService.comandaCozinha(pedido);
            return ResponseEntity.ok("comanda gerada para cozinha com sucesso!!");
        } catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @GetMapping("/totaldia")
    public RelatorioDiaDTO getTotalPedidosPorData(@RequestParam("data") LocalDate data){
        Long totalPedidos = pedidoService.TotalPedidosPorData(data);
        Long totalPedidosCartao = pedidoService.TotalPagamentoCartao(data);
        Long totalPedidosDinheiro = pedidoService.TotalPagamentoDinheiro(data);
        Long totalPedidosDelivery = pedidoService.TotalPedidosDelivery(data);
        Long totalPedidosBalcao = pedidoService.TotalPedidosBalcao(data);
        Long totalPedidosPagos = pedidoService.TotalPagos(data);
        Long totalPedidosCancelados = pedidoService.TotalCancelados(data);

        RelatorioDiaDTO relatorioDiaDTO = new RelatorioDiaDTO();
        relatorioDiaDTO.setTotalPedidos(totalPedidos);
        relatorioDiaDTO.setTotalPedidosCartao(totalPedidosCartao);
        relatorioDiaDTO.setTotalPedidosDinheiro(totalPedidosDinheiro);
        relatorioDiaDTO.setTotalPedidosDelivery(totalPedidosDelivery);
        relatorioDiaDTO.setTotalPedidosBalcao(totalPedidosBalcao);
        relatorioDiaDTO.setTotalPedidosPagos(totalPedidosPagos);
        relatorioDiaDTO.setTotalPedidosCancelados(totalPedidosCancelados);


        return relatorioDiaDTO;
    }

    @PostMapping
    public ResponseEntity <String> cadastrarPedido(@RequestBody final PedidoDTO pedidoDTO){
        try {
            this.pedidoService.cadastraPedido(pedidoDTO);
            return ResponseEntity.ok("Pedido cadastrado com sucesso!!");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editaPedido(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedidoDTO){
        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || !pedido1.getId().equals(pedidoDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o pedido informado");
            }
            this.pedidoService.atualizaPedido(pedidoDTO);
            return ResponseEntity.ok("Pedido EDITADO com sucesso!!");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/finalizaPed/{id}")
    public ResponseEntity<String> finalizaPedido(@PathVariable ("id") final Long id, @RequestBody final Pedido pedido){
        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || !pedido1.getId().equals(pedido.getId())){
                return ResponseEntity.internalServerError().body("Nao foi posivel identificar o pedido informado");
            }
            this.pedidoService.FinalizaPedido(pedido);
            return ResponseEntity.ok("PEDIDO FINALIZADO");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaPedido(@PathVariable Long id){
        try {
            this.pedidoService.excluirPedido(id);
            return ResponseEntity.ok("Pedido Excluido com sucesso!!");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

}
