package br.com.zup.mercadolivre.fechamentoCompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/retorno/")
public class FechaCompraRespostasController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private NotaFiscal notaFiscal;
	
	@Autowired
	private Ranking ranking;
	
	@PostMapping(value = "pagseguro/{id}")
	@Transactional
	public String processaPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {
		
		return processa(idCompra, request);

	}
	
	@PostMapping(value = "paypal/{id}")
	@Transactional
	public String processaPayPal(@PathVariable("id") Long idCompra, @Valid RetornoPayPalRequest request) {
		
		return processa(idCompra, request);
	}
	
	private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
		
		Compra compra = manager.find(Compra.class, idCompra);
		
		compra.adicionaTransacao(retornoGatewayPagamento);
		
		manager.merge(compra);
		
		if(compra.processadaComSucesso()) {
			
			notaFiscal.executa(compra);
			ranking.executa(compra);
//			emailSucesso.executa(compra);
			
			
		}
		
		return compra.toString();
	}
}
