package br.com.zup.mercadolivre.perguntas;

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
import br.com.zup.mercadolivre.utils.Email;

@RestController
@RequestMapping(value = "/produtos/{id}/perguntas")
public class PerguntaController {
	
	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	private Email emails;

	@PostMapping
	@Transactional
	public String criar(@RequestBody @Valid PerguntaRequest request, @PathVariable("id") Long id) {
		
		Produto produto = manager.find(Produto.class, id);
		Usuario usuario = repository.findByEmail("mel@gmail.com").get();
		
		
		Pergunta pergunta = request.toModel(usuario, produto);
		
		manager.persist(pergunta);
		
		emails.novaPergunta(pergunta);
		
		return pergunta.toString();

	}
}
