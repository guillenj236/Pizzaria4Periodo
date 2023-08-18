package br.com.uniamerica.pizzariaBack.controller;
import br.com.uniamerica.pizzariaBack.dto.PedidoDTO;
import br.com.uniamerica.pizzariaBack.entity.Pedido;
import br.com.uniamerica.pizzariaBack.repository.PedidoRep;
import br.com.uniamerica.pizzariaBack.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRep pedidoRep;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Pedido pedido = this.pedidoRep.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaPedidos(){
        return ResponseEntity.ok(this.pedidoRep.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrarPedido(@RequestBody final PedidoDTO pedidoDTO){
        try {
            this.pedidoService.cadastraPedido(pedidoDTO);
            return ResponseEntity.ok("Pedido cadastrado com sucesso!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editaPedido(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedidoDTO){

        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || pedido1.getId() != (pedidoDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o pedido informado");
            }
            this.pedidoService.atualizaPedido(pedidoDTO);
            return ResponseEntity.ok("Pedido EDITADO com sucesso!!");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaPedido(@PathVariable Long id){
        try {
            this.pedidoService.excluirPedido(id);
            return ResponseEntity.ok("Pedido Excluido com sucesso!!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
