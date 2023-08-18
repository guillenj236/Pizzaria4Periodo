package br.com.uniamerica.pizzariaBack.service;

import br.com.uniamerica.pizzariaBack.entity.Pizza;
import br.com.uniamerica.pizzariaBack.repository.PizzaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PizzaService {

    @Autowired
    private PizzaRep pizzaRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPizza(final Pizza pizza){
        assert pizza.getPrecoPizza() <= 0.0 : "O preço não pode ser nulo!!!";
        assert pizza.getQuantidadePizza() <= 0: "A quantidade de pizzas desse sabor nao pode ser nula!!";

        this.pizzaRep.save(pizza);
    }
}
