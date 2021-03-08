package br.com.zup.mercadolivre.datelheProduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produto.Produto;

@RestController
@RequestMapping(value = "/produtos/{id}")
public class DetalhesProdutosController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping
	public ProdutoResponse buscarPorId(@PathVariable("id") Long id) {
		
		Produto produtoEscolhido =  manager.find(Produto.class, id);
		return new ProdutoResponse(produtoEscolhido);

	}

}
