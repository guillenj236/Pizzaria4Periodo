package br.com.uniamerica.pizzariaBack.service;


import br.com.uniamerica.pizzariaBack.dto.EstoqueDTO;
import br.com.uniamerica.pizzariaBack.entity.EstoqueProds;
import br.com.uniamerica.pizzariaBack.repository.EstoqueProdRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EstoqueProdsService {

    @Autowired
    private EstoqueProdRep estoqueProdRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarNoEstoque(final EstoqueDTO estoqueDTO){

      var estoque = new EstoqueProds();
      BeanUtils.copyProperties(estoqueDTO,estoque);

        Assert.isTrue(!estoque.getNomeProduto().equals(""),"o nome do produto não pode ser nulo!!");
        Assert.isTrue(estoque.getNomeProduto().length() <= 40, "Máximo de caracteres alcançados!! (40)");

        EstoqueProds estoqueExistente = estoqueProdRep.findByNomeProduto(estoque.getNomeProduto());
        Assert.isTrue( estoqueExistente == null || estoqueExistente.equals(estoque), "Produto já existente!");

        this.estoqueProdRep.save(estoque);

    }

    public void atualizaEstoque (EstoqueProds estoqueProds){
        final EstoqueProds estoqueAtt=this.estoqueProdRep.findById(estoqueProds.getId()).orElse(null);

        if (estoqueAtt == null) throw new AssertionError();
        Assert.isTrue(!estoqueAtt.getNomeProduto().equals(""),"o nome do produto não pode ser nulo!!");
        Assert.isTrue(estoqueAtt.getNomeProduto().length() <= 40, "Máximo de caracteres alcançados!! (40)");

        this.estoqueProdRep.save(estoqueProds);

    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirProdEst(final Long id){

        final EstoqueProds estoqueBanco = this.estoqueProdRep.findById(id).orElse(null);

        if (estoqueBanco == null || !estoqueBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o modelo informado.");
        }
        this.estoqueProdRep.delete(estoqueBanco);

    }
}
