package br.com.uniamerica.pizzariaBack.controller;
import br.com.uniamerica.pizzariaBack.dto.FuncionarioDTO;
import br.com.uniamerica.pizzariaBack.entity.Funcionario;
import br.com.uniamerica.pizzariaBack.repository.FuncionarioRep;
import br.com.uniamerica.pizzariaBack.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRep funcionarioRep;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findByIdPath(@PathVariable("id") final Long id){
        final Funcionario funcionario = this.funcionarioRep.findById(id).orElse(null);
        return ResponseEntity.ok(funcionario);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Funcionario>> ListaFuncionarios(){
        return ResponseEntity.ok(this.funcionarioRep.findAll());
    }


    @PostMapping
    public ResponseEntity <String> cadastrarFuncionario(@RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            funcionarioService.cadastrarFuncionario(funcionarioDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editaFunc(@PathVariable ("id") final Long id, @RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            final Funcionario funcionario1 = this.funcionarioRep.findById(id).orElse(null);

            if (funcionario1 == null || !funcionario1.getId().equals(funcionarioDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.funcionarioService.atualizaFuncionario(funcionarioDTO);
            return ResponseEntity.ok("Registro EDITADO com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaFuncionario(@PathVariable Long id) {
        try {

            this.funcionarioService.excluirFuncionario(id);
            return ResponseEntity.ok("Funcionário excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
