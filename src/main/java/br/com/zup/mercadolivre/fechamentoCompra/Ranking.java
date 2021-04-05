package br.com.zup.mercadolivre.fechamentoCompra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class Ranking implements EventoCompraSucesso{

	@Override
	public void executa(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(),"opa opa opa compra nao processada com sucesso "+compra);
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idDonoProduto", compra.getDonoProduto().getId());		

		restTemplate.postForEntity("http://localhost:8080/ranking",
				request, String.class);		
	}

}