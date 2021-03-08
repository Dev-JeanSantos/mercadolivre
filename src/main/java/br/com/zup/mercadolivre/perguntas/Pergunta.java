package br.com.zup.mercadolivre.perguntas;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	private Instant momentoCriacao = Instant.now();
	private @Valid @ManyToOne Usuario usuario;
	private @Valid @ManyToOne Produto produto;

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;		
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", momentoCriacao=" + momentoCriacao + ", usuario="
				+ usuario + ", produto=" + produto + "]";
	}

	public Usuario getUsuarioInteressado() {
		// TODO Auto-generated method stub
		return usuario;
	}

	public Usuario getDonoProduto() {
	
		return produto.getUsuario();
	}

}
