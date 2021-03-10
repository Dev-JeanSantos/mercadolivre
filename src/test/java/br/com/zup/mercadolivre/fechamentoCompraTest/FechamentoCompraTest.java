package br.com.zup.mercadolivre.fechamentoCompraTest;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import br.com.zup.mercadolivre.produto.Produto;

@SpringBootTest
@AutoConfigureMockMvc
public class FechamentoCompraTest {

	@Autowired
	private MockMvc mockMvc;

	@PersistenceContext
	EntityManager manager;

	@Test
	@DisplayName("DeveRetornar200ComComprasViaPAYPAL")
	@WithMockUser("jeancbsan@gmail.com")
	public void controllerCompraTeste1() throws Exception {

		String request = "{\n" + "\"quantidade\" : 1,\n" + "\"gateway\" : \"PAGSEGURO\",\n" + "\"idProduto\": 1\n"
				+ "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/compras").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().is(200)).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@DisplayName("DeveRetornar200ComComprasViaPAYPAL")
	@WithMockUser("jeancbsan@gmail.com")
	public void controllerCompraTeste2() throws Exception {

		String request = "{\n" + "\"quantidade\" : 1,\n" + "\"gateway\" : \"PAYPAL\",\n" + "\"idProduto\": 1\n" + "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/compras").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().is(200)).andDo(MockMvcResultHandlers.print());
	}

	/**
	 * Boundary Test metodo que testamos decremento e incremento
	 */

	@SuppressWarnings("deprecation")
	@ParameterizedTest
	@CsvSource(value = { "1:true", "2:true", "100:false" }, delimiter = ':')
	@DisplayName("DeveAbaterAQuantidadeDosProdutosEmUmEstoque")
	public void abatimentoTeste(int input, Boolean status) {
		Produto produto = manager.find(Produto.class, 1L);
		Assert.isTrue(produto.abateEstoque(input) == status);
	}

	@ParameterizedTest
	@WithMockUser("email@email.br")
	@MethodSource("requests")
	@DisplayName(" DeveRetonarBadRequestNoMomentoDaCompra")
	public void compraBadRequestTest(String request) throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/compras").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print());

	}

	public static Stream<Arguments> requests() {
		return Stream.of(
				Arguments.of(
						"{\n" + "\"quantidade\" : 102,\n" + "\"gateway\" : \"PAYPAL\",\n" + "\"idProduto\": 1\n" + "}"),
				Arguments.of(
						"{\n" + "\"quantidade\" : 0,\n" + "\"gateway\" : \"PAYPAL\",\n" + "\"idProduto\": 1\n" + "}"),
				Arguments.of(
						"{\n" + "\"quantidade\" : ,\n" + "\"gateway\" : \"PAYPAL\",\n" + "\"idProduto\": 1\n" + "}"),
				Arguments.of(
						"{\n" + "\"quantidade\" : -1,\n" + "\"gateway\" : \"PAYPAL\",\n" + "\"idProduto\": 1\n" + "}"),
				Arguments.of(
						"{\n" + "\"quantidade\" : 2,\n" + "\"gateway\" : \"PAYPA\",\n" + "\"idProduto\": 1\n" + "}"),
				Arguments.of("{\n" + "\"quantidade\" : 2,\n" + "\"gateway\" : \"\",\n" + "\"idProduto\": 1\n" + "}"),
				Arguments.of(
						"{\n" + "\"quantidade\" : 2,\n" + "\"gateway\" : \"PAYPA\",\n" + "\"idProduto\": 90\n" + "}"),
				Arguments.of(
						"{\n" + "\"quantidade\" : 2,\n" + "\"gateway\" : \"PAYPA\",\n" + "\"idProduto\": \n" + "}"));
	}

}
