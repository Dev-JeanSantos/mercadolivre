package br.com.zup.mercadolivre.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping(value = "/produtos/{id}/opinioes")
public class OpiniaoController {
	
	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	UsuarioRepository repository;
	
	@PostMapping
	@Transactional
	public String criar(@RequestBody @Valid OpiniaoRequest request,@PathVariable("id") Long id) {
		
		Produto produto = manager.find(Produto.class, id);
		Usuario usuario = repository.findByEmail("jeancbsan@gmail.com").get();
		Opiniao opiniao = request.toModel(produto, usuario );
		
		manager.persist(opiniao);
		
		return opiniao.toString();

	}
}
