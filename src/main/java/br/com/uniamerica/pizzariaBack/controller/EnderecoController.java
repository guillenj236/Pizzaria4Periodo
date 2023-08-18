package br.com.uniamerica.pizzariaBack.controller;
import br.com.uniamerica.pizzariaBack.dto.EnderecoDTO;
import br.com.uniamerica.pizzariaBack.entity.Endereco;
import br.com.uniamerica.pizzariaBack.repository.EnderecoRep;
import br.com.uniamerica.pizzariaBack.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRep enderecoRep;

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Endereco endereco = this.enderecoRep.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaEnderecos(){
        return ResponseEntity.ok(this.enderecoRep.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarEndereco(@RequestBody final EnderecoDTO enderecoDTO){
        try {
            this.enderecoService.cadastrarEndereco(enderecoDTO);
            return ResponseEntity.ok("Endereco cadastrado com sucesso!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editaEnd(@PathVariable("id") final Long id, @RequestBody final EnderecoDTO enderecoDTO){
        try {
            final Endereco endereco1 = this.enderecoRep.findById(id).orElse(null);

            if (endereco1 == null || endereco1.getId() != (enderecoDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o endereco informado");
            }
            this.enderecoService.atualizaEndereco(enderecoDTO);
            return ResponseEntity.ok("Registro EDITADO com sucesso!!");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<?> deletaEnd(@PathVariable Long id){
        try {
            this.enderecoService.excluiEnd(id);
            return ResponseEntity.ok("Endereco Exluido com Sucesso!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error:" + e.getMessage());
        }
    }
}
