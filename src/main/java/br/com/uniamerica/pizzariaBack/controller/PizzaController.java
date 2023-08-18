package br.com.uniamerica.pizzariaBack.controller;

import br.com.uniamerica.pizzariaBack.dto.PizzaDTO;
import br.com.uniamerica.pizzariaBack.entity.Pizza;
import br.com.uniamerica.pizzariaBack.repository.PizzaRep;
import br.com.uniamerica.pizzariaBack.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pizza")
public class PizzaController {

    @Autowired
    private PizzaRep pizzaRep;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Pizza pizza = this.pizzaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pizza);
    }

    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.pizzaRep.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final PizzaDTO pizzaDTO){
    try {
        pizzaService.cadastrarPizza(pizzaDTO);
        return ResponseEntity.ok("Pizza cadastrada com sucesso");
    }
    catch (Exception e){
        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editaPizza(@PathVariable("id") final Long id, @RequestBody final PizzaDTO pizzaDTO){
        try {
            final Pizza pizza1 = this.pizzaRep.findById(id).orElse(null);

            if (pizza1 == null || pizza1.getId() != (pizzaDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
          ////  this.pizzaService.atualizPizza(pizza);
            return ResponseEntity.ok("Registro EDITADO com Sucesso");
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
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        try {

            this.pizzaService.excluirPizza(id);
            return ResponseEntity.ok("Pizza excluída!!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
