package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.EstoqueDTO;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import br.com.uniamerica.pizzariaback.repository.EstoqueProdRep;
import br.com.uniamerica.pizzariaback.service.EstoqueProdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/estoqueProd")
public class EstoqueProdsController {

    @Autowired
    private EstoqueProdRep estoqueProdRep;

    @Autowired
    private EstoqueProdsService estoqueProdsService;

   @GetMapping("/{id}")
   public ResponseEntity<EstoqueProds> findByIdPath(@PathVariable("id") final Long id){
       final EstoqueProds estoqueProds = this.estoqueProdRep.findById(id).orElse(null);
       return ResponseEntity.ok(estoqueProds);
   }

    @GetMapping("/lista")
    public ResponseEntity <List<EstoqueProds>> listaCompleta(){
        return ResponseEntity.ok(this.estoqueProdRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarEstoque(@RequestBody final EstoqueDTO estoqueDTO){
        try {
            estoqueProdsService.cadastrarNoEstoque(estoqueDTO);
            return ResponseEntity.ok("Produto cadastrado com sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarEstoque(@PathVariable("id") final Long id, @RequestBody final EstoqueProds estoqueProds){
        try {
            estoqueProdsService.atualizaEstoque(estoqueProds);
            final EstoqueProds estoqueProds1 = this.estoqueProdRep.findById(id).orElse(null);

            if (estoqueProds1 == null || !estoqueProds1.getId().equals(estoqueProds.getId())){
                throw new RegistroNaoEncontradoException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Produto editado no estoque com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deletaNoEstoque(@PathVariable("id") final Long id) {
        try {
           this.estoqueProdsService.excluirProdEst(id);
            return ResponseEntity.ok("Produto excluído com sucesso!!");
        } catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

   public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

}

