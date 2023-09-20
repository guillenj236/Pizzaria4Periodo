package br.com.uniamerica.pizzariaBack.controller;
import br.com.uniamerica.pizzariaBack.dto.LoginDTO;
import br.com.uniamerica.pizzariaBack.entity.Login;
import br.com.uniamerica.pizzariaBack.repository.LoginRep;
import br.com.uniamerica.pizzariaBack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private LoginRep loginRep;

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    public ResponseEntity<Login> findByIdPath(@PathVariable("id") final Long id){
        final Login login = this.loginRep.findById(id).orElse(null);
        return ResponseEntity.ok(login);
    }
    @GetMapping("/lista")
    public ResponseEntity <List<Login>> ListaLogin(){
        return ResponseEntity.ok(this.loginRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarLogin(@RequestBody final LoginDTO loginDTO){
        try {
            this.loginService.cadastraLogin(loginDTO);
            return ResponseEntity.ok("Login cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <String> editarUser(@PathVariable("id") final Long id, @RequestBody final Login login){
        try {
         loginService.atualizaLogin(login);
         final Login login1 = this.loginRep.findById(id).orElse(null);

            if (login1 == null || !login1.getId().equals(login.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o login informado");
            }
            return ResponseEntity.ok("LOGIN editado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.loginService.excluirLogin(id);
            return ResponseEntity.ok("Login Excluído com sucesso!!!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
