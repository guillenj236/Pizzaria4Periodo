package br.com.uniamerica.pizzariaback.controller;

import br.com.uniamerica.pizzariaback.dto.SaboresDTO;
import br.com.uniamerica.pizzariaback.entity.Sabores;
import br.com.uniamerica.pizzariaback.repository.SaboresRep;
import br.com.uniamerica.pizzariaback.service.SaboresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sabores")
public class SaboresController {

   @Autowired
    private SaboresRep saboresRep;

   @Autowired
    private SaboresService saboresService;


    @GetMapping("/{id}")
    public ResponseEntity<Sabores> findByIdPath(@PathVariable("id") final Long id){
        final Sabores sabores = this.saboresRep.findById(id).orElse(null);
        return ResponseEntity.ok(sabores);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Sabores>> listaSabores(){
        return ResponseEntity.ok(this.saboresRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarSabores(@RequestBody final SaboresDTO saboresDTO){
        try {
            saboresService.cadastarSabor(saboresDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editaSabor(@PathVariable("id") final Long id, @RequestBody final Sabores sabores){
        try {
            saboresService.atualizaSabor(sabores);
            final Sabores sabores1 = this.saboresRep.findById(id).orElse(null);

            if (sabores1 == null || !sabores1.getId().equals(sabores.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Sabor editado com Sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }


    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deletaSabor(@PathVariable("id") final Long id) {
        try {
                this.saboresService.excluirSabor(id);
                return ResponseEntity.ok("Sabor excluído com sucesso!!");
        } catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }


}
