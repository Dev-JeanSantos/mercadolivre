package br.com.zup.mercadolivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Min(1) @Max(5) int nota;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String descricao;
	private @Valid @ManyToOne Usuario usuario;
	private @Valid @ManyToOne Produto produto;

	public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@Valid Usuario usuario, @Valid Produto produto) {
				this.nota = nota;
				this.titulo = titulo;
				this.descricao = descricao;
				this.usuario = usuario;
				this.produto = produto;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario="
				+ usuario + ", produto=" + produto + "]";
	}
}
