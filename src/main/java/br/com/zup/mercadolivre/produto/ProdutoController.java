package br.com.zup.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.handler.exceptions.validations.ProibeDuplicidadeNoNomeDaCaracteristica;
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
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new ProibeDuplicidadeNoNomeDaCaracteristica());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Produto> novoProduto(@Valid @RequestBody ProdutoRequest request) {
		
		Usuario usuario = repository.findByEmail("jeancbsan@gmail.com");
		
		Produto produto = request.toModel(manager, usuario);
		if (produto != null) {
			manager.persist(produto);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
