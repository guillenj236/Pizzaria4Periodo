package br.com.uniamerica.pizzariaBack.service;


import br.com.uniamerica.pizzariaBack.dto.EstoqueDTO;
import br.com.uniamerica.pizzariaBack.entity.EstoqueProds;
import br.com.uniamerica.pizzariaBack.repository.EstoqueProdRed;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueProdsService {

    @Autowired
    private EstoqueProdRed estoqueProdRed;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarNoEstoque(final EstoqueDTO estoqueDTO){

      var estoque = new EstoqueProds();
      BeanUtils.copyProperties(estoqueDTO,estoque);




    }
}
