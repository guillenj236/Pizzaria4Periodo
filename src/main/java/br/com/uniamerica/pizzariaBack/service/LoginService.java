package br.com.uniamerica.pizzariaBack.service;
import br.com.uniamerica.pizzariaBack.dto.LoginDTO;
import br.com.uniamerica.pizzariaBack.entity.Login;
import br.com.uniamerica.pizzariaBack.repository.LoginRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LoginService {

    @Autowired
    private LoginRep loginRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraLogin(final LoginDTO loginDTO){
        var login = new Login();
        BeanUtils.copyProperties(loginDTO,login);

        Assert.isTrue(login.getNomeLogin().length() <= 30, "Maximo de caracteres alcançados");
        Assert.isTrue(login.getSenhaLogin().length() <= 20, "Maximo de caracteres na senha alcançados");

        Assert.isTrue(!login.getNomeLogin().equals(""), "O nome do login não pode ser nulo");
        Assert.isTrue(!login.getSenhaLogin().equals(""),"A senha nao pode ser nula!!");

        Login loginExist = loginRep.findByNomeLogin(login.getNomeLogin());
        Assert.isTrue(loginExist == null || loginExist.equals(login), "Login já existente!!");

        this.loginRep.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaLogin(Login login){

        Login loginExistente = this.loginRep.findById(login.getId()).orElse(null);
        Assert.isTrue(loginExistente == null || !loginExistente.equals(login), "Login nulo ou nao certo");

        this.loginRep.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirLogin(final Long id){

        final Login loginBanco = this.loginRep.findById(id).orElse(null);

        if (loginBanco == null || !loginBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o login informado.");
        }
        this.loginRep.delete(loginBanco);
    }
}
