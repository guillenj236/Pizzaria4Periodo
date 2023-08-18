package br.com.uniamerica.pizzariaBack.controller;
import br.com.uniamerica.pizzariaBack.dto.UsuarioDTO;
import br.com.uniamerica.pizzariaBack.entity.Usuario;
import br.com.uniamerica.pizzariaBack.repository.UsuarioRep;
import br.com.uniamerica.pizzariaBack.service.UsuarioService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRep usuarioRep;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioRep.findById(id).orElse(null);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaUsers(){
        return ResponseEntity.ok(this.usuarioRep.findAll());
    }

    @GetMapping("/nome/{nomeUsuario}")
    public ResponseEntity<?> NomeBusca(@PathVariable("nomeUsuario") String nomeUsuario){
        Usuario usuario = usuarioRep.findByNomeUsuario(nomeUsuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUser(@RequestBody final UsuarioDTO usuarioDTO){
        try {
            this.usuarioService.cadastraUsuario(usuarioDTO);
            return ResponseEntity.ok("Usuario cadastrado com sucesso!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable("id") final Long id, @RequestBody final UsuarioDTO usuarioDTO){
        try {
            final Usuario usuario1 = this.usuarioRep.findById(id).orElse(null);

            if (usuario1 == null || usuario1.getId() != (usuarioDTO.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel identificar o usuario informado");
            }
            this.usuarioService.atualizaUsuario(usuarioDTO);
            return ResponseEntity.ok("Registro EDITADO com sucesso");
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
    public ResponseEntity<?> deletaUsuario(@PathVariable Long id) {
        try {
            this.usuarioService.excluirUsuario(id);
            return ResponseEntity.ok("usuario Excluido");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}
