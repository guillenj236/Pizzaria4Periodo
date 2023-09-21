package DtoUtest;
import br.com.uniamerica.pizzariaback.dto.LoginDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginDtoTests {

     LoginDTO loginDTO = new LoginDTO("TesteLogin","senhateste");
     LoginDTO loginDTO2 = new LoginDTO();

     @Test
    void getSetLoginTest(){
         loginDTO.setNomeLogin("login");
         Assertions.assertEquals("login",loginDTO.getNomeLogin());
     }
     @Test
    void getSet2SenhaTest(){
         loginDTO.setSenhaLogin("senha");
         Assertions.assertEquals("senha",loginDTO.getSenhaLogin());
     }
     @Test
    void idTest(){
         loginDTO.setId(2L);
         Assertions.assertEquals(2L,loginDTO.getId());
     }
     @Test
     void comparacaoNomeTest(){
         LoginDTO loginDTO2 = new LoginDTO("TesteLogin","senhateste");
        Assertions.assertEquals(loginDTO.getNomeLogin(), loginDTO2.getNomeLogin());
     }
     @Test
     void comparacaoSenhaTest(){
         LoginDTO loginDTO2 = new LoginDTO("TesteLogin","senhateste");
         Assertions.assertEquals(loginDTO.getSenhaLogin(), loginDTO2.getSenhaLogin());
     }
     @Test
    void toStringTest(){
         LoginDTO loginDTO2 = new LoginDTO("TesteLogin","senhateste");
         Assertions.assertEquals(loginDTO2.toString(),loginDTO.toString());
     }
     @Test
    void construtorVazioTest(){
         LoginDTO loginDTO3 = new LoginDTO();
         Assertions.assertEquals(loginDTO2,loginDTO3);
     }
     @Test
    void hashCodeLogin(){
         LoginDTO loginDTO1 = new LoginDTO("adm","adm");
         LoginDTO loginDTO2 = new LoginDTO("adm","adm");

         Assertions.assertEquals(loginDTO1.hashCode(),loginDTO2.hashCode());
     }
}
