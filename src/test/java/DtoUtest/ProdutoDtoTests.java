package DtoUtest;
import br.com.uniamerica.pizzariaback.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProdutoDtoTests {

    EstoqueProds estoqueProds = new EstoqueProds(1L,66,"TapaNaOreia");
    ProdutosDTO produtosDTO = new ProdutosDTO(2,33,estoqueProds);
    ProdutosDTO produtosDTO2 = new ProdutosDTO();

    @Test
    void getSetQuantidadeTest(){
        produtosDTO.setQuantidadeprod(10);
        Assertions.assertEquals(10,produtosDTO.getQuantidadeprod());
    }
    @Test
    void getSetPrecoTest(){
        produtosDTO.setTotalprod(99);
        Assertions.assertEquals(99,produtosDTO.getTotalprod());
    }
    @Test
    void construtorVazioTest(){
        ProdutosDTO produtosDTO3 = new ProdutosDTO();
        Assertions.assertEquals(produtosDTO2,produtosDTO3);
    }
    @Test
    void idTest(){
        produtosDTO.setId(2L);
        Assertions.assertEquals(2L,produtosDTO.getId());
    }
    @Test
    void comparacaoTest(){
        ProdutosDTO produtosDTO2 = new ProdutosDTO(2,33,estoqueProds);
        Assertions.assertEquals(produtosDTO,produtosDTO2);
    }
    @Test
    void hashCodeTest(){
        ProdutosDTO produtosDTO1 = new ProdutosDTO(2,33,estoqueProds);
        ProdutosDTO produtosDTO2 = new ProdutosDTO(2,33,estoqueProds);
        Assertions.assertEquals(produtosDTO1.hashCode(),produtosDTO2.hashCode());
    }
    @Test
    void toStringTest(){
        ProdutosDTO produtosDTO2 = new ProdutosDTO(2,33,estoqueProds);
        Assertions.assertEquals(produtosDTO2.toString(),produtosDTO.toString());
    }
 }
