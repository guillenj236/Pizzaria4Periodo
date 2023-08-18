package br.com.uniamerica.pizzariaBack.controller;

import br.com.uniamerica.pizzariaBack.entity.Pizza;
import br.com.uniamerica.pizzariaBack.repository.PizzaRep;
import br.com.uniamerica.pizzariaBack.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> cadastrar (@RequestBody final Pizza pizza){
    try {
        pizzaService.cadastrarPizza(pizza);
        return ResponseEntity.ok("Registro cadastrado com sucesso");
    }
    catch (Exception e){
        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
    }
    }
}
