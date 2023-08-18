package br.com.uniamerica.pizzariaBack.controller;


import br.com.uniamerica.pizzariaBack.entity.EstoqueProds;
import br.com.uniamerica.pizzariaBack.repository.EstoqueProdRed;
import br.com.uniamerica.pizzariaBack.service.EstoqueProdsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/estoqueProd")
public class EstoqueProdsController {

    @Autowired
    private EstoqueProdRed estoqueProdRed;

    @Autowired
    private EstoqueProdsService estoqueProdsService;

   @GetMapping("/{id}")
   public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
       final EstoqueProds estoqueProds = this.estoqueProdRed.findById(id).orElse(null);
       return ResponseEntity.ok(estoqueProds);
   }

    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.estoqueProdRed.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrarEstoque(@RequestBody final EstoqueProds estoqueProds){
        try {
            //estoqueProdsService.cadastrarMarca(marca);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarEstoque(@PathVariable("id") final Long id, @RequestBody final EstoqueProds estoqueProds){
        try {
           // estoqueProdsService.atualizarMarca(marca);
            final EstoqueProds estoqueProds1 = this.estoqueProdRed.findById(id).orElse(null);

            if (estoqueProds1 == null || !estoqueProds1.getId().equals(estoqueProds.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Produto cadastrado no estoque com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<?> deletaNoEstoque(@PathVariable("id") final Long id) {
        try {
         //   this.estoqueProdsService.excluirSabor(id);
            return ResponseEntity.ok("Produto excluído com sucesso!!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
