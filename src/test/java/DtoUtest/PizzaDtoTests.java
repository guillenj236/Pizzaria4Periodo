package DtoUtest;

import br.com.uniamerica.pizzariaback.dto.PizzaDTO;
import br.com.uniamerica.pizzariaback.entity.Sabores;
import br.com.uniamerica.pizzariaback.entity.Tamanho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PizzaDtoTests {
     private final List<Sabores> saboresList = new ArrayList<>();
     PizzaDTO pizzaDTO = new PizzaDTO(saboresList,30,2, Tamanho.P);
     PizzaDTO pizzaDTO2 = new PizzaDTO();

     @Test
    void getSetPrecoPizza(){
         pizzaDTO.setPrecoPizza(40);
         Assertions.assertEquals(40,pizzaDTO.getPrecoPizza());
     }
     @Test
    void getSetQuantidadePizza(){
         pizzaDTO.setQuantidadePizza(9);
         Assertions.assertEquals(9,pizzaDTO.getQuantidadePizza());
     }
     @Test
    void getSetTamanhoPizza(){
         pizzaDTO.setTamanho(Tamanho.GG);
         Assertions.assertEquals(Tamanho.GG,pizzaDTO.getTamanho());
     }
     @Test
     void getSetTamanho2(){
         pizzaDTO.setTamanho(Tamanho.P);
         Assertions.assertEquals(Tamanho.P,pizzaDTO.getTamanho());

     }
    @Test
    void getSetTamanho3(){
        pizzaDTO.setTamanho(Tamanho.M);
        Assertions.assertEquals(Tamanho.M,pizzaDTO.getTamanho());
    }
    @Test
    void getSetTamanho4(){
        pizzaDTO.setTamanho(Tamanho.G);
        Assertions.assertEquals(Tamanho.G,pizzaDTO.getTamanho());
    }
     @Test
    void construtorVazioTest(){
         PizzaDTO pizzaDTO3 = new PizzaDTO();
         Assertions.assertEquals(pizzaDTO2,pizzaDTO3);
     }
     @Test
    void idTeste(){
         pizzaDTO.setId(2L);
         Assertions.assertEquals(2L,pizzaDTO.getId());
     }
     @Test
    void comparacaoTeste(){
         PizzaDTO pizzaDTO2 = new PizzaDTO(saboresList,30,2, Tamanho.P);
         Assertions.assertEquals(pizzaDTO,pizzaDTO2);
     }
     @Test
    void hashCodeTest(){
         PizzaDTO pizzaDTO1 = new PizzaDTO(saboresList,30,2, Tamanho.P);
         PizzaDTO pizzaDTO2 = new PizzaDTO(saboresList,30,2, Tamanho.P);
         Assertions.assertEquals(pizzaDTO1.hashCode(),pizzaDTO2.hashCode());
     }
     @Test
    void testToString(){
         PizzaDTO pizzaDTO2 = new PizzaDTO(saboresList,30,2, Tamanho.P);
         Assertions.assertEquals(pizzaDTO2.toString(),pizzaDTO.toString());
     }
}
