package br.com.zup.mercadolivre.opiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class OpiniaoRequest {
	
	@Min(1)
	@Max(5)
	int nota;
	@NotBlank
	String titulo;
	@NotBlank
	@Size(max =  500)
	String descricao;
	
	public OpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "OpiniaoRequest [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		
		return new Opiniao(nota, titulo, descricao, usuario, produto);
	}
	
}
