package br.com.uniamerica.pizzariaBack.service;

import br.com.uniamerica.pizzariaBack.dto.UsuarioDTO;
import br.com.uniamerica.pizzariaBack.entity.Usuario;
import br.com.uniamerica.pizzariaBack.repository.UsuarioRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRep usuarioRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastraUsuario(final UsuarioDTO usuarioDTO){

        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO,usuario);

        this.usuarioRep.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = this.usuarioRep.findById(usuarioDTO.getId()).orElse(null);

        if (usuarioExistente != null) {

            BeanUtils.copyProperties(usuarioDTO,usuarioExistente);

            this.usuarioRep.save(usuarioExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirUsuario(final Long id){

        final Usuario usuarioBanco = this.usuarioRep.findById(id).orElse(null);

        if (usuarioBanco == null || usuarioBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o usuario informado.");
        }
        this.usuarioRep.save(usuarioBanco);
    }

}
