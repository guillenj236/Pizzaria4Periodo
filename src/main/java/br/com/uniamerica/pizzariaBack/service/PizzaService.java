package br.com.uniamerica.pizzariaBack.service;

import br.com.uniamerica.pizzariaBack.dto.PizzaDTO;
import br.com.uniamerica.pizzariaBack.entity.Pizza;
import br.com.uniamerica.pizzariaBack.entity.Tamanho;
import br.com.uniamerica.pizzariaBack.repository.PizzaRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PizzaService {

    @Autowired
    private PizzaRep pizzaRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPizza(final PizzaDTO pizzaDTO){
        var pizza = new Pizza();
        BeanUtils.copyProperties(pizzaDTO,pizza);

        float total;

        Assert.isTrue(pizza.getSabores() != null, "Selecione ao menos um sabor na pizza!!");
        Assert.isTrue(pizza.getQuantidadePizza() != 0,"Adicione a quantidade de pizzas");

        if(pizza.getTamanho() == Tamanho.P){

            Assert.isTrue(pizza.getSabores().size() == 1,"Este tamanho só tem 1 sabor!!");
            pizza.setPrecoPizza(24);
        }
        if(pizza.getTamanho() == Tamanho.M){

            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <= 2,"Este tamanho só tem de 1 a 2 sabores!!");
            pizza.setPrecoPizza(30);
        }
        if(pizza.getTamanho() == Tamanho.G){

            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <= 3,"Este tamanho tem de 1 a 3 sabores!!");
            pizza.setPrecoPizza(45);
        }
        if(pizza.getTamanho() == Tamanho.GG){

            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <= 4,"Este tamanho tem de 1 a 4 sabores!!");
            pizza.setPrecoPizza(55);
        }

        total = pizza.getPrecoPizza() * pizza.getQuantidadePizza();
        pizza.setPrecoPizza(total);

        this.pizzaRep.save(pizza);
    }


    @Transactional(rollbackFor = Exception.class)
    public void atualizPizza(PizzaDTO pizzaDTO) {

        Pizza pizzaExistente = this.pizzaRep.findById(pizzaDTO.getId()).orElse(null);


        if (pizzaExistente != null) {

            BeanUtils.copyProperties(pizzaDTO, pizzaExistente);


            this.pizzaRep.save(pizzaExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPizza(final Long id){

        final Pizza pizzaBanco = this.pizzaRep.findById(id).orElse(null);

        if (pizzaBanco == null || !pizzaBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o pizza informado.");
        }
        this.pizzaRep.delete(pizzaBanco);
    }
}
