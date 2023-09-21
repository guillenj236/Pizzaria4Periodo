package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.EnderecoDTO;
import br.com.uniamerica.pizzariaback.entity.Endereco;
import br.com.uniamerica.pizzariaback.repository.EnderecoRep;
import br.com.uniamerica.pizzariaback.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRep enderecoRep;

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findByIdPath(@PathVariable("id") final Long id){
        final Endereco endereco = this.enderecoRep.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Endereco>> ListaEnderecos(){
        return ResponseEntity.ok(this.enderecoRep.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastrarEndereco(@RequestBody final EnderecoDTO enderecoDTO){
        try {
            this.enderecoService.cadastrarEndereco(enderecoDTO);
            return ResponseEntity.ok("Endereco cadastrado com sucesso!");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editaEnd(@PathVariable("id") final Long id, @RequestBody final EnderecoDTO enderecoDTO){
        try {
            final Endereco endereco1 = this.enderecoRep.findById(id).orElse(null);

            if (endereco1 == null || !endereco1.getId().equals(enderecoDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o endereco informado");
            }
            this.enderecoService.atualizaEndereco(enderecoDTO);
            return ResponseEntity.ok("Registro EDITADO com sucesso!!");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deletaEnd(@PathVariable Long id){
        try {
            this.enderecoService.excluiEnd(id);
            return ResponseEntity.ok("Endereco Exluido com Sucesso!");
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
