package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.LoginDTO;
import br.com.uniamerica.pizzariaback.entity.Login;
import br.com.uniamerica.pizzariaback.repository.LoginRep;
import br.com.uniamerica.pizzariaback.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity <List<Login>> listaLogin(){
        return ResponseEntity.ok(this.loginRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarLogin(@RequestBody final LoginDTO loginDTO){
        try {
            this.loginService.cadastraLogin(loginDTO);
            return ResponseEntity.ok("Login cadastrado com sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
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
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.loginService.excluirLogin(id);
            return ResponseEntity.ok("Login Excluído com sucesso!!!");
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
