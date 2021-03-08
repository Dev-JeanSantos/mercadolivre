package br.com.zup.mercadolivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@PersistenceContext
	EntityManager manager;		
	
	@Autowired
	private ProibeUsuarioComEmailDuplicadoValidator proibeUsuarioComEmailDuplicadoValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeUsuarioComEmailDuplicadoValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarUsuarios(@RequestBody @Valid UsuarioRequest usuarioRequest) {
		
		Usuario usuario = usuarioRequest.toModel();
		
		if(usuario != null) {
			
			manager.persist(usuario);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();

	}
}
