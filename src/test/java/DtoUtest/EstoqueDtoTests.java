package DtoUtest;

import br.com.uniamerica.pizzariaback.dto.EstoqueDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EstoqueDtoTests {

     EstoqueDTO estoqueDTO = new EstoqueDTO(77, "linguica");
     EstoqueDTO estoqueDTO2 = new EstoqueDTO();

     @Test
    void getSetProd(){
         estoqueDTO.setNomeProduto("Dragonite");
         Assertions.assertEquals("Dragonite", estoqueDTO.getNomeProduto());
     }
     @Test
    void getSetPreco(){
         estoqueDTO.setPrecoProdutos(15);
         Assertions.assertEquals(15, estoqueDTO.getPrecoProdutos());
     }
     @Test
     void idTest(){
         estoqueDTO.setId(2L);
         Assertions.assertEquals(2L, estoqueDTO.getId());
     }
     @Test
     void toStringTest(){
         EstoqueDTO estoqueDTO2 = new EstoqueDTO(77,"linguica");
         Assertions.assertEquals(estoqueDTO2.toString(),estoqueDTO.toString());
     }

     @Test
    void construtorVazioTest(){
         EstoqueDTO estoqueDTO3 = new EstoqueDTO();
         Assertions.assertEquals(estoqueDTO2,estoqueDTO3);
     }

     @Test
    void comparacaoTest(){
         EstoqueDTO estoqueDTO2 = new EstoqueDTO(77, "linguica");
         Assertions.assertEquals(estoqueDTO,estoqueDTO2);
     }

     @Test
    void hashCodeTest(){
         EstoqueDTO estoqueDTO1 = new EstoqueDTO(77,"linguica");
         EstoqueDTO estoqueDTO2 = new EstoqueDTO(77,"linguica");

         Assertions.assertEquals(estoqueDTO1.hashCode(),estoqueDTO2.hashCode());
     }


}
