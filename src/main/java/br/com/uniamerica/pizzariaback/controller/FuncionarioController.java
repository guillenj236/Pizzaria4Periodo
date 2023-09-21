package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.FuncionarioDTO;
import br.com.uniamerica.pizzariaback.entity.Funcionario;
import br.com.uniamerica.pizzariaback.repository.FuncionarioRep;
import br.com.uniamerica.pizzariaback.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity <List<Funcionario>> listaFuncionarios(){
        return ResponseEntity.ok(this.funcionarioRep.findAll());
    }


    @PostMapping
    public ResponseEntity <String> cadastrarFuncionario(@RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            funcionarioService.cadastrarFuncionario(funcionarioDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
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
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaFuncionario(@PathVariable Long id) {
        try {

            this.funcionarioService.excluirFuncionario(id);
            return ResponseEntity.ok("Funcionário excluído");
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
