package br.com.uniamerica.pizzariaback.service;
import br.com.uniamerica.pizzariaback.dto.UsuarioDTO;
import br.com.uniamerica.pizzariaback.entity.Usuario;
import br.com.uniamerica.pizzariaback.repository.UsuarioRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRep usuarioRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraUsuario(final UsuarioDTO usuarioDTO){

        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO,usuario);

        Assert.isTrue(usuario.getTelefone().length() <= 15, "Limite de caracteres excedido para TELEFONE");
        Assert.isTrue(!usuario.getTelefone().equals(""), "O TELEFONE não pode ser nulo!!");
        Assert.isTrue(usuario.getNomeUsuario().length() <= 50, "Maximo de caracteres excedidos para Nome");
        Assert.isTrue(!usuario.getNomeUsuario().equals(""), "Nome nao pode ser nulo!!");

        Usuario usuarioExist = usuarioRep.findByNomeUsuario(usuario.getNomeUsuario());
        Assert.isTrue(usuarioExist == null || usuarioExist.equals(usuario), "Este usuario já existe!!");

        this.usuarioRep.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaUsuario(Usuario usuario) {
         this.usuarioRep.save(usuario);

    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirUsuario(final Long id){

        final Usuario usuarioBanco = this.usuarioRep.findById(id).orElse(null);

        if (usuarioBanco == null || !usuarioBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possivel identificar o usuario informado.");
        }
        this.usuarioRep.save(usuarioBanco);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

}
