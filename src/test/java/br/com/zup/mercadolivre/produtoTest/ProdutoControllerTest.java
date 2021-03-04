package br.com.zup.mercadolivre.produtoTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRequest;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadolivre.usuario.SenhaLimpa;
import br.com.zup.mercadolivre.usuario.Usuario;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {
	@Autowired
	private MockMvc mock;

	@DisplayName("Um produto precisa de 03 caracteristicas")
	@ParameterizedTest
	@MethodSource("geradorTest1")
	void test1(Collection<CaracteristicaRequest> caracteristicas) throws Exception {
		Categoria categoria = new Categoria("categoria");
		Usuario usuario = new Usuario("jeancbsan@gmail.com", new SenhaLimpa("senhaa"));
		new Produto("SMART TV", 0, null, null, categoria, usuario, caracteristicas);
	}

	static Stream<Arguments> geradorTest1() {
		return Stream.of(
				Arguments.of(List.of(new CaracteristicaRequest("key", "value"),
						new CaracteristicaRequest("key2", "value2"), new CaracteristicaRequest("key3", "value3"))),
				Arguments.of(List.of(new CaracteristicaRequest("key", "value"),
						new CaracteristicaRequest("key2", "value2"), new CaracteristicaRequest("key3", "value3"),
						new CaracteristicaRequest("key4", "value4"))));
	}

	@DisplayName("Um produto não deve ser criado com menos de 03 caracteristicas")
	@ParameterizedTest
	@MethodSource("geradorTest2")
	void test2(Collection<CaracteristicaRequest> caracteristicas) throws Exception {
		Categoria categoria = new Categoria("categoria");
		Usuario usuario = new Usuario("jeancbsan@gmail.com", new SenhaLimpa("senhaa"));

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Produto("SMART TV", 0, null, null, categoria, usuario, caracteristicas);
		});
	}
	
	static Stream<Arguments> geradorTest2() {
		return Stream.of(
				Arguments.of(List.of(new CaracteristicaRequest("key", "value"),
						new CaracteristicaRequest("key2", "value2"))),
				Arguments.of(List.of(new CaracteristicaRequest("key", "value"))));
	}
	
	
	@DisplayName("Cria um produtos com varias caracterisiticas distintas")
	@ParameterizedTest
	@MethodSource("geradorTest3")
	void test3(int esperado, List<CaracteristicaRequest> caracteristicas) throws Exception {
		
		ProdutoRequest req = new ProdutoRequest("Celular", 10, BigDecimal.TEN, "Tela 6,2 Polegadas", 1l , caracteristicas);

		Assertions.assertEquals(esperado, req.buscarCaracteristicasIguais().size());
	}
	
	static Stream<Arguments> geradorTest3() {
		return Stream.of(
				Arguments.of(0, List.of()),
				Arguments.of(0, List.of(new CaracteristicaRequest("key", "value"))),
				Arguments.of(0, List.of(new CaracteristicaRequest("key", "value"), new CaracteristicaRequest("key1", "value1"))),
				Arguments.of(1, List.of(
						new CaracteristicaRequest("key", "value"), 
						new CaracteristicaRequest("key", "value")))
						);				
	}
	
	@Test
	@WithMockUser(username = "jeancbsan@gmail.com", password = "123456")
	public void DeveriaRetornarStatus400AoPassarIdCategoriaNaoExistente() throws Exception {	
		String req = "{\n" + "    \"nome\" : \"Tenis\",\n" + "    \"valor\" : 1089.0,\n"
				+ "    \"descricao\" : \"Adidas usado \",\n" + "    \"quantidade\" : 1,\n" + "    \"idCategoria\" : 10,\n"
				+ "    \"caracteristicas\" : [\n" + "        {\"nome\" : \"Marca\",\"descricao\" : \"Adidas\"} ,\n"
				+ "        { \"nome\" : \"Cor \" , \"descricao\" : \"Branco\"},\n"
				+ "        {\"nome\" : \"Desconto\", \"descricao\": \"10% a prazo\"}    \n" + "    ]\n" + "}";
		
		mock.perform(MockMvcRequestBuilders.post("/produtos").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(req))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}	
	
}
