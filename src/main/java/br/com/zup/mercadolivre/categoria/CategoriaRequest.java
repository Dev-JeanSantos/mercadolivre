package br.com.zup.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.zup.mercadolivre.handler.exceptions.validations.UniqueValue;

public class CategoriaRequest {

	@NotBlank(message = "O nome não pode estar vazio")
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@Positive
	private Long categoria;

	public CategoriaRequest(String nome, Long categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	@Transactional
	public Categoria toModel(EntityManager manager) {
		Categoria novaCategoria = new Categoria(this.nome);
		if (this.categoria != null) {
			Categoria check = manager.find(Categoria.class, this.categoria);
			Assert.isTrue(check != null, "Categoria mãe não existe");
			novaCategoria.setCategoria(check);
			return novaCategoria;
		} else {
			return novaCategoria;
		}
	}	
}
