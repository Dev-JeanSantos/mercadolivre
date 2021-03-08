package br.com.zup.mercadolivre.perguntas;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	String titulo;

	@Deprecated
	public PerguntaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitulo() {
		return titulo;
	}



	public Pergunta toModel(Usuario usuario, Produto produto) {
		
		return new Pergunta(titulo, usuario, produto);
	}

}
