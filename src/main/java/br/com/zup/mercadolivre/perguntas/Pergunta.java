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
public class Pergunta implements Comparable<Pergunta>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	private Instant momentoCriacao = Instant.now();
	private @Valid @ManyToOne Usuario usuario;
	private @Valid @ManyToOne Produto produto;
	
	@Deprecated
	public Pergunta() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public int compareTo(Pergunta o) {
		// TODO Auto-generated method stub
		return this.titulo.compareTo(o.titulo);
	}

	public String getPerguntas() {
		// TODO Auto-generated method stub
		return titulo;
	}
}
