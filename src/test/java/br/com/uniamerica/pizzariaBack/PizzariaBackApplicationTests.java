package br.com.uniamerica.pizzariaBack;
import br.com.uniamerica.pizzariaBack.controller.*;
import br.com.uniamerica.pizzariaBack.dto.*;
import br.com.uniamerica.pizzariaBack.entity.*;
import br.com.uniamerica.pizzariaBack.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PizzariaBackApplicationTests {

	@MockBean
	UsuarioRep usuarioRep;
	@Autowired
	UsuarioController usuarioController;
	private List<Usuario> usuarioList;

	@MockBean
	EnderecoRep enderecoRep;
	@Autowired
	EnderecoController enderecoController = new EnderecoController();
	private List<Endereco> enderecoList;

	@MockBean
	FuncionarioRep funcionarioRep;
	@Autowired
	FuncionarioController funcionarioController = new FuncionarioController();
	private List<Funcionario> funcionarioList;

	@MockBean
	SaboresRep saboresRep;
	@Autowired
	SaboresController saboresController = new SaboresController();
	private List<Sabores> saboresList;

	@MockBean
	LoginRep loginRep;
	@Autowired
	LoginController loginController = new LoginController();
	private List<Login> loginList;


	@BeforeEach
	void injectData(){
		Usuario usuario = new Usuario(1L, "Gabriel", "45998036059");
		Usuario usuario2 = new Usuario(2L, "Joao", "45999690045");
		usuarioList = new ArrayList<>();
		usuarioList.add(usuario);
		usuarioList.add(usuario2);

		Mockito.when(usuarioRep.save(usuario)).thenReturn(usuario);
		Mockito.when(usuarioRep.save(usuario2)).thenReturn(usuario2);
		Mockito.when(usuarioRep.findById(1L)).thenReturn(Optional.of(usuario));
		Mockito.when(usuarioRep.findById(2L)).thenReturn(Optional.of(usuario2));
		Mockito.when(usuarioRep.findAll()).thenReturn(usuarioList);

		Endereco endereco = new Endereco(1L,"RUAteste","BAIRROteste",530,usuario);
		Endereco endereco2 = new Endereco(2L, "RUAdois", "BAIRROdois",777,usuario2);
		enderecoList = new ArrayList<>();
		enderecoList.add(endereco);
		enderecoList.add(endereco2);

		Mockito.when(enderecoRep.save(endereco)).thenReturn(endereco);
		Mockito.when(enderecoRep.save(endereco2)).thenReturn(endereco2);
		Mockito.when(enderecoRep.findById(1L)).thenReturn(Optional.of(endereco));
		Mockito.when(enderecoRep.findById(2L)).thenReturn(Optional.of(endereco2));
		Mockito.when(enderecoRep.findAll()).thenReturn(enderecoList);

		Login login = new Login("nomeTeste","senhaTeste");
		Login login2 = new Login("nomeDOIS", "senhaDOIS");
		loginList = new ArrayList<>();
		loginList.add(login);
		loginList.add(login2);

		Mockito.when(loginRep.save(login)).thenReturn(login);
		Mockito.when(loginRep.save(login2)).thenReturn(login2);
		Mockito.when(loginRep.findById(1L)).thenReturn(Optional.of(login));
		Mockito.when(loginRep.findById(2L)).thenReturn(Optional.of(login2));
		Mockito.when(loginRep.findAll()).thenReturn(loginList);

		Funcionario funcionario = new Funcionario(1L,"David");
		Funcionario funcionario2 = new Funcionario(2L,"PAINHO");
		funcionarioList = new ArrayList<>();
		funcionarioList.add(funcionario);
		funcionarioList.add(funcionario2);

		Mockito.when(funcionarioRep.save(funcionario)).thenReturn(funcionario);
		Mockito.when(funcionarioRep.save(funcionario2)).thenReturn(funcionario2);
		Mockito.when(funcionarioRep.findById(1L)).thenReturn(Optional.of(funcionario));
		Mockito.when(funcionarioRep.findById(2L)).thenReturn(Optional.of(funcionario2));
		Mockito.when(funcionarioRep.findAll()).thenReturn(funcionarioList);
	}

	@Test
	void testCriaUser(){
		var usuario = usuarioController.cadastrarUser(new UsuarioDTO("Lindao","4599999999"));

		Assertions.assertEquals("Usuario cadastrado com sucesso!!", usuario.getBody());
	}

	@Test
	void DeleteUser(){
		var usuario = usuarioController.deletaUsuario(1L);
		Assertions.assertEquals("usuario Excluido", usuario.getBody());
	}

	@Test
	void findAllUser(){
		ResponseEntity<List<Usuario>> usuarioFuncaoController = usuarioController.ListaUsers();
		List<Usuario> usuarioListController = usuarioFuncaoController.getBody();

		Assertions.assertNotNull(usuarioListController);
		for (int i=0; i<usuarioList.size();i ++){
			Assertions.assertEquals(usuarioList.get(i), usuarioListController.get(i));
		}
	}

	@Test
	public void testCriaEndereco(){
		Usuario usuarioTest = new Usuario(3L, "Terceiro","994038950");
		var	endereco = enderecoController.cadastrarEndereco(new EnderecoDTO("RuaTOMA","BairroTOMA",530,usuarioTest));
		Assertions.assertEquals("Endereco cadastrado com sucesso!", endereco.getBody());
	}

	@Test
	public void testPUTendereco(){
		Usuario usuarioTestPut = new Usuario(4L, "Quarto", "444444444");
		EnderecoDTO enderecoDTO = new EnderecoDTO("RuaPUT", "BairroPUT", 666, usuarioTestPut);
		enderecoDTO.setId(1L);

		var endereco = enderecoController.editaEnd(1L, enderecoDTO);
		Assertions.assertEquals("Registro EDITADO com sucesso!!", endereco.getBody());
	}

	@Test
	void testDELETEendereco(){
		var endereco = enderecoController.deletaEnd(2L);
		Assertions.assertEquals("Endereco Exluido com Sucesso!", endereco.getBody());
	}

	@Test
	void testFindByIDendereco(){
		Usuario usuario = new Usuario(5L,"QUINTO", "45999335511");
		enderecoController.cadastrarEndereco(new EnderecoDTO("RuaTOMA","BairroTOMA",530,usuario));
		var endereco = enderecoController.findByIdPath(1L);
		Assertions.assertEquals(endereco.getBody().getRua(), enderecoController.findByIdPath(1L).getBody().getRua());
	}

	@Test
	void testFindAllEndereco(){
		ResponseEntity<List<Endereco>> enderecoFuncaoController = enderecoController.ListaEnderecos();
		List<Endereco> enderecoListaController = enderecoFuncaoController.getBody();

		Assertions.assertNotNull(enderecoListaController);
		for (int i = 0; i< enderecoList.size(); i ++){
			Assertions.assertEquals(enderecoList.get(i), enderecoListaController.get(i));
		}
	}

	@Test
	void testCriaFuncionario(){
		var funcionario = funcionarioController.cadastrarFuncionario(new FuncionarioDTO("Morador"));
		Assertions.assertEquals("Registro cadastrado com sucesso",funcionario.getBody());
	}

	@Test
	void testPUTfuncionario(){
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO("FuncionarioPUT");
		funcionarioDTO.setId(1L);

		var funcionario = funcionarioController.editaFunc(1L, funcionarioDTO);
		Assertions.assertEquals("Registro EDITADO com Sucesso", funcionario.getBody());
	}

	@Test
	void testDELETEfunc(){
		var funcionarioDelete = funcionarioController.deletaFuncionario(1L);
		Assertions.assertEquals("Funcionário excluído",funcionarioDelete.getBody());
	}

	@Test
	void testFindByIdFuncionario(){
		funcionarioController.cadastrarFuncionario(new FuncionarioDTO("Ichigo"));
		var funcionario = funcionarioController.findByIdPath(1L);
		Assertions.assertEquals(funcionario.getBody().getNomeFunc(), funcionarioController.findByIdPath(1L).getBody().getNomeFunc());
	}

	@Test
	void testFindAllFuncionario(){
		ResponseEntity<List<Funcionario>> funcionarioFuncaoController = funcionarioController.ListaFuncionarios();
		List<Funcionario> funcionariosListController = funcionarioFuncaoController.getBody();

		Assertions.assertNotNull(funcionariosListController);
		for (int i = 0; i<funcionarioList.size(); i++){
			Assertions.assertEquals(funcionarioList.get(i), funcionariosListController.get(i));
		}
	}
}
