package br.com.uniamerica.pizzariaBack.controller;

import br.com.uniamerica.pizzariaBack.dto.SaboresDTO;
import br.com.uniamerica.pizzariaBack.entity.Sabores;
import br.com.uniamerica.pizzariaBack.repository.SaboresRep;
import br.com.uniamerica.pizzariaBack.service.SaboresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sabores")
public class SaboresController {

   @Autowired
    private SaboresRep saboresRep;

   @Autowired
    private SaboresService saboresService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Sabores sabores = this.saboresRep.findById(id).orElse(null);
        return ResponseEntity.ok(sabores);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaSabores(){
        return ResponseEntity.ok(this.saboresRep.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrarSabores(@RequestBody final SaboresDTO saboresDTO){
        try {
            saboresService.cadastarSabor(saboresDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editaSabor(@PathVariable("id") final Long id, @RequestBody final Sabores sabores){
        try {
            saboresService.atualizaSabor(sabores); // MUDAR AQUI EM MUDAR AQUI
            final Sabores sabores1 = this.saboresRep.findById(id).orElse(null);

            if (sabores1 == null || !sabores1.getId().equals(sabores.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Sabor editado com Sucesso");
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
    public ResponseEntity<?> deletaSabor(@PathVariable("id") final Long id) {
        try {
                this.saboresService.excluirSabor(id);
                return ResponseEntity.ok("Sabor excluído com sucesso!!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
