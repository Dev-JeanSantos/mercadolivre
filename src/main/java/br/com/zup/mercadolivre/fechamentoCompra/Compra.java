package br.com.zup.mercadolivre.fechamentoCompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

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
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
	@Deprecated
	public Compra() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	public void adicionaTransacao(@Valid RetornoPagSeguroRequest request) {
		
		Transacao novaTransacao = request.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Transação já existente " + novaTransacao);
		
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().
				filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Compra Concluida com sucesso ");
		this.transacoes.add(novaTransacao);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produtoComprado=" + produtoComprado + ", quantidade=" + quantidade
				+ ", usuarioComprador=" + usuarioComprador + ", gateway=" + gateway + ", transacoes=" + transacoes
				+ "]";
	}
}
