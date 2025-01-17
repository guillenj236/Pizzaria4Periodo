package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaback.entity.Produtos;
import br.com.uniamerica.pizzariaback.repository.ProdutosRep;
import br.com.uniamerica.pizzariaback.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutosController {

    @Autowired
    ProdutosRep produtosRep;

    @Autowired
    ProdutosService produtosService;

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> findByIdPath(@PathVariable("id") final Long id){
        final Produtos produtos = this.produtosRep.findById(id).orElse(null);
        return ResponseEntity.ok(produtos);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Produtos>> listaProdutos(){
        return ResponseEntity.ok(this.produtosRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarProdutos(@RequestBody final ProdutosDTO produtosDTO){
        try {
           produtosService.cadastrarProduto(produtosDTO);
            return ResponseEntity.ok("Produto feito com sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final ProdutosDTO produtosDTO){
        try {
            final Produtos produto1 = this.produtosRep.findById(id).orElse(null);

            if (produto1 == null || !produto1.getId().equals(produtosDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.produtosService.atualizaProduto(produtosDTO);
            return ResponseEntity.ok("Produto Editado com Sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProdutos(@PathVariable Long id) {
        try {

            this.produtosService.excluirProduto(id);
            return ResponseEntity.ok("Produto Excluido Com Sucesso");
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
