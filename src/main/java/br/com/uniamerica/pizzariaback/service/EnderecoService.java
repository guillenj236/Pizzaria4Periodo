package br.com.uniamerica.pizzariaback.service;

import br.com.uniamerica.pizzariaback.dto.EnderecoDTO;
import br.com.uniamerica.pizzariaback.entity.Endereco;
import br.com.uniamerica.pizzariaback.repository.EnderecoRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRep enderecoRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarEndereco (final EnderecoDTO enderecoDTO){

        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO,endereco);

        Assert.isTrue(endereco.getBairro() != null, "Bairro nao pode ser nulo!!");
        Assert.isTrue(endereco.getBairro().length() <= 30, "Maximo de caracteres para bairro alcancado!");
        Assert.isTrue(endereco.getRua() != null, "A rua nao pode ser nula!!");
        Assert.isTrue(endereco.getRua().length() <= 30, "Maximo de caracteres para rua alcancados!!");


        this.enderecoRep.save(endereco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaEndereco(EnderecoDTO enderecoDTO){
        Endereco enderecoExistente = this.enderecoRep.findById(enderecoDTO.getId()).orElse(null);

            BeanUtils.copyProperties(enderecoDTO, enderecoExistente);


            this.enderecoRep.save(enderecoExistente);

    }

    @Transactional(rollbackFor = Exception.class)
    public void excluiEnd(final Long id){

        final Endereco enderecoBanco = this.enderecoRep.findById(id).orElse(null);

        if (enderecoBanco == null || enderecoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o endereco informado.");
        }
        this.enderecoRep.delete(enderecoBanco);
    }
}
