package DtoUtest;

import br.com.uniamerica.pizzariaback.dto.FuncionarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class FuncionarioDtoTests {

  FuncionarioDTO funcionarioDTO = new FuncionarioDTO("Robertin");
  FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO();
  @Test
 void getSetNomeTest(){
   funcionarioDTO.setNomeFunc("MajinBoo");
   Assertions.assertEquals("MajinBoo",funcionarioDTO.getNomeFunc());
  }
  @Test
  void testeId(){
   funcionarioDTO.setId(2L);
   Assertions.assertEquals(2L, funcionarioDTO.getId());
  }
  @Test
  void testToString(){
    FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO("Robertin");
    Assertions.assertEquals(funcionarioDTO2.toString(),funcionarioDTO.toString());
  }
  @Test
     void construtorVazioTest(){
      FuncionarioDTO funcionarioDTO3 = new FuncionarioDTO();
      Assertions.assertEquals(funcionarioDTO2,funcionarioDTO3);
  }
  @Test
     void hashCodeTest(){
      FuncionarioDTO funcionarioDTO1 = new FuncionarioDTO("Lenda");
      FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO("Lenda");
      Assertions.assertEquals(funcionarioDTO1.hashCode(),funcionarioDTO2.hashCode());
  }
  @Test
     void comparacaoTest(){
      FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO("Robertin");
      Assertions.assertEquals(funcionarioDTO,funcionarioDTO2);
  }
}
