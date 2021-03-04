package br.com.zup.mercadolivre.usuarioTest;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.mercadolivre.usuario.UsuarioRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTeste {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EntityManager manager;

	@Transactional
	private void seedBD() {
		UsuarioRequest usuario = new UsuarioRequest("jeancbsa@gmail.com", "123456");
		manager.persist(usuario);
	}

	String usuarioRequest = "{\n" +
            "    \"email\" : \"jeancbsa@gmail.com\",\n" +
            "    \"senha\" : \"123456\"\n" +
            "}";
	

	@Test
	@WithMockUser
	@DisplayName("deveriaRetornarStatus200AoSalvarUsuarioNoBanco")
	public void criaUsuarioTeste() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.usuarioRequest))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@WithMockUser
	@DisplayName("DeveriaReconhecerDuplicacaoDeEmailERetornarStautus404")
	@Transactional
	public void emailDuplicadoTeste() throws Exception {

		String req = "{\n" +
                "    \"email\" : \"jeancbsan@gmail.com\",\n" +
                "    \"senha\" : \"123457\"\n" +
                "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(req))
				.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}

	@Test
	@DisplayName("DeveriaRetornarErro404CasoNaoPasseNenhumEmail")
	@WithMockUser
	public void emailVazioTeste() throws Exception {

		 String nuloOuVazio = "{\n" +
	                "    \"email\" : \"\",\n" +
	                "    \"senha\" : \"123456\"\n" +
	                "}";

		mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(nuloOuVazio))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
    @WithMockUser
    @DisplayName("DeveriaLidarComEmailInvalido")
    public void emailFormatoInvalidoTeste() throws  Exception{

        String emailErroFormatacao = "{\n" +
                "    \"email\" : \"qualquer.comemail\",\n" +
                "    \"senha\" : \"123456\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(emailErroFormatacao)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    @WithMockUser
    @DisplayName("DeveriaLidaerComSenhaVaziaOuNula")
    public void senhaBrancaOuNulaTeste() throws  Exception{

        String senhaVaziaENula = "{\n" +
                "    \"email\" : \"john@email.com\",\n" +
                "    \"senha\" : \"\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(senhaVaziaENula)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    @DisplayName("DeveriaLidarSenhaMenorQueSeisCaracteres")
    @WithMockUser
    public void senhaSemTamanhoMinimo() throws  Exception{

        String senhaMenorQueSeis = "{\n" +
                "    \"email\" : \"john@email.com\",\n" +
                "    \"senha\" : \"12345\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(senhaMenorQueSeis)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
