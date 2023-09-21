package br.com.uniamerica.pizzariaback.controller;

import br.com.uniamerica.pizzariaback.dto.PizzaDTO;
import br.com.uniamerica.pizzariaback.entity.Pizza;
import br.com.uniamerica.pizzariaback.repository.PizzaRep;
import br.com.uniamerica.pizzariaback.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pizza")
public class PizzaController {

    @Autowired
    private PizzaRep pizzaRep;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findByIdPath(@PathVariable("id") final Long id){
        final Pizza pizza = this.pizzaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pizza);
    }

    @GetMapping("/lista")
    public ResponseEntity <List<Pizza>> listaCompleta(){
        return ResponseEntity.ok(this.pizzaRep.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastrar (@RequestBody final PizzaDTO pizzaDTO){
    try {
        pizzaService.cadastrarPizza(pizzaDTO);
        return ResponseEntity.ok("Pizza cadastrada com sucesso");
    }
    catch (RuntimeException e){
        String errorMessage = getErrorMessage(e);
        return ResponseEntity.internalServerError().body(errorMessage);
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editaPizza(@PathVariable("id") final Long id, @RequestBody final PizzaDTO pizzaDTO){
        try {
            final Pizza pizza1 = this.pizzaRep.findById(id).orElse(null);

            if (pizza1 == null || !pizza1.getId().equals(pizzaDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar a pizza informado");
            }
          this.pizzaService.atualizPizza(pizzaDTO);
            return ResponseEntity.ok("pizza EDITADA com Sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.pizzaService.excluirPizza(id);
            return ResponseEntity.ok("Pizza excluída!!");
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
