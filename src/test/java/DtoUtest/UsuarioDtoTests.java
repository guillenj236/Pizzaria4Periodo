package DtoUtest;
import br.com.uniamerica.pizzariaback.dto.UsuarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class UsuarioDtoTests {

    UsuarioDTO usuarioDTO = new UsuarioDTO("NewUser","4599980239");
    UsuarioDTO usuarioDTO2 = new UsuarioDTO();
    @Test
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
    void comparacaoTest(){
        UsuarioDTO usuarioDTO2 = new UsuarioDTO("NewUser","4599980239");
        Assertions.assertEquals(usuarioDTO.getNomeUsuario(),usuarioDTO2.getNomeUsuario());
    }
    @Test
    void comparacaoTelefoneTest(){
        UsuarioDTO usuarioDTO2 = new UsuarioDTO("NewUser","4599980239");
        Assertions.assertEquals(usuarioDTO.getTelefone(),usuarioDTO2.getTelefone());
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
    @Test
     void construtorVazioTest(){
        UsuarioDTO usuarioDTO3 = new UsuarioDTO();
        Assertions.assertEquals(usuarioDTO2, usuarioDTO3);
    }
    @Test
     void hashCodeTest(){
        UsuarioDTO usuarioDTO1 = new UsuarioDTO("aaa","bbb");
        UsuarioDTO usuarioDTO2 = new UsuarioDTO("aaa","bbb");

        Assertions.assertEquals(usuarioDTO1.hashCode(),usuarioDTO2.hashCode());
    }
}
