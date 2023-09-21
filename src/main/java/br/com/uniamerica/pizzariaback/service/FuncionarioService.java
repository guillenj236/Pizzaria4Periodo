package br.com.uniamerica.pizzariaback.service;

import br.com.uniamerica.pizzariaback.dto.FuncionarioDTO;
import br.com.uniamerica.pizzariaback.entity.Funcionario;
import br.com.uniamerica.pizzariaback.repository.FuncionarioRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRep funcionarioRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarFuncionario(final FuncionarioDTO funcionarioDTO){
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO,funcionario);

        Assert.isTrue(!funcionario.getNomeFunc().equals(""), "O nome do funcionario nao pode ser nulo!!");
        Assert.isTrue(funcionario.getNomeFunc().length() <= 40, "Maximo de caracteres para nome excedido!");

        this.funcionarioRep.save(funcionario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaFuncionario(FuncionarioDTO funcionarioDTO) {

        Funcionario funcionarioExistente = this.funcionarioRep.findById(funcionarioDTO.getId()).orElse(null);


        if (funcionarioExistente != null) {

            BeanUtils.copyProperties(funcionarioDTO, funcionarioExistente);


            this.funcionarioRep.save(funcionarioExistente);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void excluirFuncionario(final Long id){

        final Funcionario funcionarioBanco = this.funcionarioRep.findById(id).orElse(null);

        if (funcionarioBanco == null || !funcionarioBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o funcionario informado.");
        }
        this.funcionarioRep.delete(funcionarioBanco);
    }

}
