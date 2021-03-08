package br.com.zup.mercadolivre.datelheProduto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import br.com.zup.mercadolivre.produto.Produto;

public class ProdutoResponse {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	
	private Set<DetalhesProdutosCaracteristica> caracteristicas;
	private Set<String> linkImagens;
	private SortedSet<String> perguntas;

	public ProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		
		this.caracteristicas = produto.mapCaracteristicas(DetalhesProdutosCaracteristica :: new);
		
		this.linkImagens = produto.mapeiaImagem(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getPerguntas());
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}

	public Set<DetalhesProdutosCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinkImagens() {
		return linkImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}
}
