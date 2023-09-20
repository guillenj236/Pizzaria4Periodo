package br.com.uniamerica.pizzariaBack;
import br.com.uniamerica.pizzariaBack.controller.EnderecoController;
import br.com.uniamerica.pizzariaBack.controller.LoginController;
import br.com.uniamerica.pizzariaBack.controller.SaboresController;
import br.com.uniamerica.pizzariaBack.controller.UsuarioController;
import br.com.uniamerica.pizzariaBack.dto.*;
import br.com.uniamerica.pizzariaBack.entity.Endereco;
import br.com.uniamerica.pizzariaBack.entity.Login;
import br.com.uniamerica.pizzariaBack.entity.Sabores;
import br.com.uniamerica.pizzariaBack.entity.Usuario;
import br.com.uniamerica.pizzariaBack.repository.EnderecoRep;
import br.com.uniamerica.pizzariaBack.repository.LoginRep;
import br.com.uniamerica.pizzariaBack.repository.SaboresRep;
import br.com.uniamerica.pizzariaBack.repository.UsuarioRep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PizzariaBackApplicationTests {

	@MockBean
	UsuarioRep usuarioRep;
	@Autowired
	private final UsuarioController usuarioController = new UsuarioController();
	private List<Usuario> usuarioList;

	@MockBean
	EnderecoRep enderecoRep;
	@Autowired
	private final EnderecoController enderecoController = new EnderecoController();
	private List<Endereco> enderecoList;

	@MockBean
	SaboresRep saboresRep;
	@Autowired
	private final SaboresController saboresController = new SaboresController();
	private List<Sabores> saboresList;

	@MockBean
	LoginRep loginRep;
	@Autowired
	private final LoginController loginController = new LoginController();
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




	}

	@Test
	public void testSabores(){
		SaboresDTO saboresDTO = new SaboresDTO();
		saboresDTO.setSaborPizza("Sabor_Teste");
		Assert.isTrue(!saboresDTO.getSaborPizza().equals(""),"O nome do sabor não pode ser nulo!!");
		Assert.isTrue(saboresDTO.getSaborPizza().length() <= 70, "Máximo de caracteres alcançados");
	}

	@Test
	public void testFuncionario(){
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

		funcionarioDTO.setNomeFunc("Funcionario_Test");

		Assert.isTrue(!funcionarioDTO.getNomeFunc().equals(""), "O nome do funcionario nao pode ser nulo!!");
		Assert.isTrue(funcionarioDTO.getNomeFunc().length() <= 40, "Maximo de caracteres para nome excedido!");
	}
	@Test
	public void testEstoqueProduto(){
		EstoqueDTO estoqueDTO = new EstoqueDTO();

		estoqueDTO.setNomeProduto("Produto_Teste");
		estoqueDTO.setPrecoProdutos(3);

		Assert.isTrue(!estoqueDTO.getNomeProduto().equals(""),"o nome do produto não pode ser nulo!!");
		Assert.isTrue(estoqueDTO.getNomeProduto().length() <= 40, "Máximo de caracteres alcançados!! (40)");

		Assertions.assertEquals("Produto_Teste", estoqueDTO.getNomeProduto());
	}

	@Test
	void testEndereco(){
		EnderecoDTO enderecoDTO = new EnderecoDTO();

		enderecoDTO.setBairro("Bairro Teste");
		enderecoDTO.setRua("Rua Teste");
		enderecoDTO.setNumeroEnd(777);

		Assert.isTrue(enderecoDTO.getBairro() != null, "Bairro nao pode ser nulo!!");
		Assert.isTrue(enderecoDTO.getBairro().length() <= 30, "Maximo de caracteres para bairro alcancado!");
		Assert.isTrue(enderecoDTO.getRua() != null, "A rua nao pode ser nula!!");
		Assert.isTrue(enderecoDTO.getRua().length() <= 30, "Maximo de caracteres para rua alcancados!!");

		Assertions.assertEquals("Bairro Teste", enderecoDTO.getBairro());
	}


	@Test
	void testeLogin(){
		LoginDTO loginDTO = new LoginDTO();

		loginDTO.setNomeLogin("GabrielTeste");
		loginDTO.setSenhaLogin("senhaDeTeste");

		Assert.isTrue(loginDTO.getNomeLogin().length() <= 100, "Maximo de caracteres alcançado");
	}

}
