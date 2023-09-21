package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.UsuarioDTO;
import br.com.uniamerica.pizzariaback.entity.Usuario;
import br.com.uniamerica.pizzariaback.repository.UsuarioRep;
import br.com.uniamerica.pizzariaback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRep usuarioRep;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdPath(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioRep.findById(id).orElse(null);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Usuario>> listaUsers(){
        return ResponseEntity.ok(this.usuarioRep.findAll());
    }

    @GetMapping("/nome/{nomeUsuario}")
    public ResponseEntity<Usuario> nomeBusca(@PathVariable("nomeUsuario") String nomeUsuario){
        Usuario usuario = usuarioRep.findByNomeUsuario(nomeUsuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarUser(@RequestBody final UsuarioDTO usuarioDTO){
        try {
            usuarioService.cadastraUsuario(usuarioDTO);
            return ResponseEntity.ok("Usuario cadastrado com sucesso!!");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable("id") final Long id, @RequestBody final Usuario usuario){
        try {
            usuarioService.atualizaUsuario(usuario);
            final Usuario usuario1 = this.usuarioRep.findById(id).orElse(null);
            if (usuario1 == null || !usuario1.getId().equals(usuario.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o usuario informado");
            }
            return ResponseEntity.ok("USUARIO editado com Sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deletaUsuario(@PathVariable Long id) {
        try {
            this.usuarioService.excluirUsuario(id);
            return ResponseEntity.ok("usuario Excluido");
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
