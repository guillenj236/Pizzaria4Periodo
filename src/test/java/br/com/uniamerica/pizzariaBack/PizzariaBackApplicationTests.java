package br.com.uniamerica.pizzariaBack;

import br.com.uniamerica.pizzariaBack.dto.EstoqueDTO;
import br.com.uniamerica.pizzariaBack.dto.FuncionarioDTO;
import br.com.uniamerica.pizzariaBack.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaBack.dto.SaboresDTO;
import br.com.uniamerica.pizzariaBack.service.SaboresService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class PizzariaBackApplicationTests {

	@Autowired
	SaboresService saboresService;

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
	}

}
