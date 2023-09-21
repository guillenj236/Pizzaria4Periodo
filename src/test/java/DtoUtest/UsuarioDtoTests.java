package DtoUtest;

import br.com.uniamerica.pizzariaback.dto.UsuarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class UsuarioDtoTests {

    UsuarioDTO usuarioDTO = new UsuarioDTO("NewUser","4599980239");@Test

    void getSetNomeTest(){
        usuarioDTO.setNomeUsuario("Dracula");
        Assertions.assertEquals("Dracula", usuarioDTO.getNomeUsuario());
    }
    @Test
    void testToString(){
        UsuarioDTO usuarioDTO2 = new UsuarioDTO("NewUser","4599980239");
        Assertions.assertEquals(usuarioDTO2.toString(), usuarioDTO.toString());
    }
    @Test
     void idTest(){
        usuarioDTO.setId(2L);
        Assertions.assertEquals(2L, usuarioDTO.getId());
    }

    @Test
     void testSimplesUsuario(){
        UsuarioDTO usuarioDTO1 = new UsuarioDTO();
        usuarioDTO1.setNomeUsuario("Simples");
        Assertions.assertEquals("Simples",usuarioDTO1.getNomeUsuario());
    }

    @Test
     void testSimplesUsuarioCompleto(){
        UsuarioDTO usuarioDTO10 = new UsuarioDTO();
        usuarioDTO10.setNomeUsuario("BenDeiz");
        usuarioDTO10.setTelefone("45999690045");
        Assertions.assertEquals("BenDeiz",usuarioDTO10.getNomeUsuario());
        Assertions.assertEquals("45999690045",usuarioDTO10.getTelefone());
    }
}
