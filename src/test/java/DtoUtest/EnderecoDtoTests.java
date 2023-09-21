package DtoUtest;

import br.com.uniamerica.pizzariaback.dto.EnderecoDTO;
import br.com.uniamerica.pizzariaback.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class EnderecoDtoTests {

    Usuario usuario = new Usuario(1L, "A","45999690045");
    EnderecoDTO enderecoDTO = new EnderecoDTO("RuaDTO","BairroDTO",530,usuario);
    EnderecoDTO enderecoDTO2 = new EnderecoDTO();

    @Test
    void getSetTest(){
        enderecoDTO.setRua("Linguica");
        Assertions.assertEquals("Linguica",enderecoDTO.getRua());
    }
    @Test
    void getSetBairroTest(){
        enderecoDTO.setBairro("Cristal");
        Assertions.assertEquals("Cristal", enderecoDTO.getBairro());
    }
    @Test
    void getSetNumeroTest(){
        enderecoDTO.setNumeroEnd(999);
        Assertions.assertEquals(999,enderecoDTO.getNumeroEnd());
    }
    @Test
    void construtorVazioTest(){
        EnderecoDTO enderecoDTO3 = new EnderecoDTO();
        Assertions.assertEquals(enderecoDTO2,enderecoDTO3);
    }
    @Test
    void idTest(){
        enderecoDTO.setId(2L);
        Assertions.assertEquals(2L, enderecoDTO.getId());
    }
    @Test
    void comparacaoTest(){
        EnderecoDTO enderecoDTO2 = new EnderecoDTO("RuaDTO","BairroDTO",530,usuario);
        Assertions.assertEquals(enderecoDTO,enderecoDTO2);
    }
    @Test
    void hashCodeTest(){
        EnderecoDTO enderecoDTO1 = new EnderecoDTO("RuaDTO","BairroDTO",530,usuario);
        EnderecoDTO enderecoDTO2 = new EnderecoDTO("RuaDTO","BairroDTO",530,usuario);

        Assertions.assertEquals(enderecoDTO1.hashCode(), enderecoDTO2.hashCode());
    }

    @Test
    void toStringTest(){
        EnderecoDTO enderecoDTO2 = new EnderecoDTO("RuaDTO","BairroDTO",530,usuario);
        Assertions.assertEquals(enderecoDTO2.toString(), enderecoDTO.toString());
    }


}
