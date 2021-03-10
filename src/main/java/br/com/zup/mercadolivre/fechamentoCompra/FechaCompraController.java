package br.com.zup.mercadolivre.fechamentoCompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping(value = "/compras")
public class FechaCompraController {	
	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	UsuarioRepository repository;
	
	@PostMapping
	@Transactional
	public String criar(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
		
		Produto produtoComprado = manager.find(Produto.class, request.getIdProduto());
		
		int quantidade = request.getQuantidade();
		
		boolean abateu = produtoComprado.abateEstoque(quantidade);
		Gateway gateway = request.getGateway();
		
		if(abateu) {
			Usuario usuarioComprador = repository.findByEmail("mel@gmail.com").get();
			Compra novaCompra = new Compra(produtoComprado, quantidade, usuarioComprador,gateway);
			
			manager.persist(novaCompra);
			
			if(gateway.equals(Gateway.PAGSEGURO)) {
				
				UriComponents urlPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").
					buildAndExpand(novaCompra.getId().toString());
				
				return "pagseguro.com/" + novaCompra.getId() +
						"?redirectUrl=" + urlPagSeguro;
			}else {
				
				UriComponents urlPayPal = uriComponentsBuilder.path("/retorno-paypal/{id}").
						buildAndExpand(novaCompra.getId().toString());
					
					return "paypal.com/" + novaCompra.getId() +
							"?redirectUrl=" + urlPayPal;	
			}
		}
		
		//Excelente saída para exceçoes utilizando o próprio framework
		BindException erroEstoque = new BindException(request, "novaCompraRequest");
		erroEstoque.reject(null, "Houve um erro no estoque!");
		
		throw erroEstoque;

	}
}
