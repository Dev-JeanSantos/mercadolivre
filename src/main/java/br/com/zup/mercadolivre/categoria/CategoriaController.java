package br.com.zup.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	    @PersistenceContext
	    private EntityManager manager;

	    @PostMapping
	    @Transactional
	    public ResponseEntity<?> novaCategoria(@Valid @RequestBody CategoriaRequest request){
	        Categoria categoria = request.toModel(manager);
	        if(categoria != null){
	            manager.persist(categoria);
	            return ResponseEntity.ok().build();
	        }
	        return ResponseEntity.badRequest().build();
	    }
}
