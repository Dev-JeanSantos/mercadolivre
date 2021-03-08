package br.com.zup.mercadolivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.handler.exceptions.validations.ProibeDuplicidadeNoNomeDaCaracteristica;
import br.com.zup.mercadolivre.produto.imagem.ImagensProdutosRequest;
import br.com.zup.mercadolivre.produto.imagem.Uploader;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
//	@Autowired
//	private AuthUtils auth ;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private Uploader uploaderfake;
	
	@InitBinder(value = "ProdutoRequest")
	public void init(WebDataBinder binder) {
		binder.addValidators(new ProibeDuplicidadeNoNomeDaCaracteristica());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Produto> novoProduto(@Valid @RequestBody ProdutoRequest request) {
		
		Usuario usuario = repository.findByEmail("jeancbsan@gmail.com").get();
		
		Produto produto = request.toModel(manager, usuario);
		if (produto != null) {
			manager.persist(produto);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public String adicionaImagens(@PathVariable("id") Long id, @Valid ImagensProdutosRequest imagemRequest) {
		/*
		 * Passo 1 - Upload das Imagens para o servidor de imagens
		 * Passo 2 - Pegar os Links de todas as imagens já hospedadas no servidor
		 * Passo 3 - Associar os links a cada produto em questão
		 * Passo 4 - Carregar o produto
		 * Passo 5 - Atualizar o produto com as associaçoes dos link realizadas 
		 */
		
		Usuario usuario = repository.findByEmail("jeancbsan@gmail.com").get();
		Produto produto = manager.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(usuario)) {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		
		
		//Passo 1 e 2
		Set<String> links = uploaderfake.envia(imagemRequest.getImages());
		
		//Passo 3
		produto.associaProdutos(links);
		
		manager.merge(produto);
		return produto.toString();

	}

}
