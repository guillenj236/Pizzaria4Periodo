package br.com.uniamerica.pizzariaBack.service;

import br.com.uniamerica.pizzariaBack.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaBack.entity.Produtos;
import br.com.uniamerica.pizzariaBack.entity.Sabores;
import br.com.uniamerica.pizzariaBack.repository.ProdutosRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ProdutosService {

    @Autowired
    ProdutosRep produtosRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarProduto (final ProdutosDTO produtosDTO){

        var produtos = new Produtos();
        BeanUtils.copyProperties(produtosDTO,produtos);

        Assert.isTrue(produtos.getQuantidade_prod() != 0, "A quantidade do produto não pode ser nula!!");

        Assert.isTrue(produtos.getEstoqueProds() != null, "O Produto não pode ser nulo!!");

        this.produtosRep.save(produtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaProduto(ProdutosDTO produtoDTO) {

        Produtos produtoExistente = this.produtosRep.findById(produtoDTO.getId()).orElse(null);


        if (produtoExistente != null) {

            BeanUtils.copyProperties(produtoDTO, produtoExistente);


            this.produtosRep.save(produtoExistente);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void excluirProduto(final Long id){

        final Produtos produtoBanco = this.produtosRep.findById(id).orElse(null);

        if (produtoBanco == null || produtoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o produto informado.");
        }
        this.produtosRep.delete(produtoBanco);
    }
}
