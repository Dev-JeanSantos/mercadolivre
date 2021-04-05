package br.com.zup.mercadolivre.fechamentoCompra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscal implements EventoCompraSucesso{

	@Override
	public void executa(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(),"Compra nao concluida com sucesso "+compra);
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idComprador", compra.getComprador().getId());		

		restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
				request, String.class);
	}

}