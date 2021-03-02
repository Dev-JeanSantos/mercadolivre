package br.com.zup.mercadolivre.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EvitaEmailDuplicadoValidation implements Validator{
	
//	@PersistenceContext
//	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return UsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UsuarioRequest usuarioRequest = (UsuarioRequest) target;
		
		Optional<Usuario> possivelUsuario = repository.findByEmail(usuarioRequest.getEmail());
		
		/*
		 * UTILIZANDO O ENTITYMANAGER 
		 * Query buscaEmailUnico = manager.createQuery("SELECT 1 FROM Usuario u WHERE u.email = :email")
		 * 		.setParameter("email", usuarioRequest.getEmail()); 
		 *  List<?> resultList = buscaEmailUnico.getResultList();
		 *  
		 * if(!resultList.isEmpty()) { errors.rejectValue("email", null,"Já existe esse email no sistema");}
		 */		
		
		if(possivelUsuario.isPresent()) {
			errors.rejectValue("email", null, "Já existe esse email no sistema");
		}
	}

}
