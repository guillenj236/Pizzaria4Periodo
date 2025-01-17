package br.com.uniamerica.pizzariaback.service;


import br.com.uniamerica.pizzariaback.dto.SaboresDTO;
import br.com.uniamerica.pizzariaback.entity.Sabores;
import br.com.uniamerica.pizzariaback.repository.SaboresRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SaboresService {

    @Autowired
    private SaboresRep saboresRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastarSabor(final SaboresDTO saboresDTO){

        var sabores = new Sabores();
        BeanUtils.copyProperties(saboresDTO,sabores);

        Assert.isTrue(!sabores.getSaborPizza().equals(""),"O nome do sabor não pode ser nulo!!");
        Assert.isTrue(sabores.getSaborPizza().length() <= 70, "Máximo de caracteres alcançados");

        Sabores saboresExist = saboresRep.findBySaborPizza(sabores.getSaborPizza());
        Assert.isTrue(saboresExist == null || saboresExist.equals(sabores), "Sabor já existente!!");


        this.saboresRep.save(sabores);
    }

    public void atualizaSabor (Sabores sabores){

        this.saboresRep.save(sabores);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirSabor(final Long id){

        final Sabores saboresBanco = this.saboresRep.findById(id).orElse(null);

        if (saboresBanco == null || !saboresBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possivel identificar o sabor informado.");
        }
        this.saboresRep.delete(saboresBanco);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }
}
