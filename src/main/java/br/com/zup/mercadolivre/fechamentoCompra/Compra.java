package br.com.zup.mercadolivre.fechamentoCompra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produtoComprado;
	@Positive
	@Valid
	private int quantidade;
	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuarioComprador;
	@Valid
	@Enumerated
	private Gateway gateway;
	
	

	public Compra(@NotNull @Valid Produto produtoComprado, 
			@Positive @Valid int quantidade, @NotNull @Valid Usuario usuarioComprador,@Valid Gateway gateway) {
		
		this.produtoComprado = produtoComprado;
		this.quantidade = quantidade;
		this.usuarioComprador = usuarioComprador;
		this.gateway = gateway;
		
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Compra [id=" + id + ", produtoComprado=" + produtoComprado + ", quantidade=" + quantidade
				+ ", usuarioComprador=" + usuarioComprador + "]";
	}
}