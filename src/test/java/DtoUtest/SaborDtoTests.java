package DtoUtest;

import br.com.uniamerica.pizzariaback.dto.SaboresDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SaborDtoTests {

    SaboresDTO saboresDTO = new SaboresDTO("Pessego");
    SaboresDTO saboresDTO2 = new SaboresDTO();

    @Test
    void getSetSaboresTest(){
        saboresDTO.setSaborPizza("Donut");
        Assertions.assertEquals("Donut",saboresDTO.getSaborPizza());
    }
    @Test
    void idTest(){
        saboresDTO.setId(2L);
        Assertions.assertEquals(2L,saboresDTO.getId());
    }
    @Test
    void toStringTest() {
    SaboresDTO saboresDTO2 = new SaboresDTO("Pessego");
    Assertions.assertEquals(saboresDTO2.toString(),saboresDTO.toString());
    }

    @Test
    void comparacaoTest(){
        SaboresDTO saboresDTO2 = new SaboresDTO("Pessego");
        Assertions.assertEquals(saboresDTO.getSaborPizza(), saboresDTO2.getSaborPizza());
    }

    @Test
    void construtorVazioTest(){
        SaboresDTO saboresDTO3 = new SaboresDTO();
        Assertions.assertEquals(saboresDTO2,saboresDTO3);
    }
}
