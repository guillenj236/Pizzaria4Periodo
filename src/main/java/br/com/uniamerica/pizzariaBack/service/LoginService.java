package br.com.uniamerica.pizzariaBack.service;
import br.com.uniamerica.pizzariaBack.dto.LoginDTO;
import br.com.uniamerica.pizzariaBack.entity.Login;
import br.com.uniamerica.pizzariaBack.repository.LoginRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private LoginRep loginRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraLogin(final LoginDTO loginDTO){

        var login = new Login();
        BeanUtils.copyProperties(loginDTO,login);

        this.loginRep.save(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaLogin(LoginDTO loginDTO){

        Login loginExistente = this.loginRep.findById(loginDTO.getId()).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirLogin(final Long id){

        final Login loginBanco = this.loginRep.findById(id).orElse(null);

        if (loginBanco == null || loginBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o login informado.");
        }
        this.loginRep.delete(loginBanco);
    }
}
