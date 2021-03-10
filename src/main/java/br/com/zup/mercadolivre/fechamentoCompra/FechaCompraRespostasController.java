package br.com.zup.mercadolivre.fechamentoCompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/retorno-pagseguro/{id}")
public class FechaCompraRespostasController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public String processaPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {
		
		Compra compra = manager.find(Compra.class, idCompra);
		
		compra.adicionaTransacao(request);
		
		manager.merge(compra);
		
		return compra.toString();

	}
}
