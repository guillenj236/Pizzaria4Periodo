package br.com.uniamerica.pizzariaBack.controller;

import br.com.uniamerica.pizzariaBack.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaBack.entity.Produtos;
import br.com.uniamerica.pizzariaBack.repository.ProdutosRep;
import br.com.uniamerica.pizzariaBack.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
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
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProdutos(@PathVariable Long id) {
        try {

            this.produtosService.excluirProduto(id);
            return ResponseEntity.ok("Produto Excluido Com Sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
