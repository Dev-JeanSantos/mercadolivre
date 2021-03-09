package br.com.zup.mercadolivre.datelheProduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

import br.com.zup.mercadolivre.produto.Produto;

public class ProdutoResponse {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	
	private Set<DetalhesProdutosCaracteristica> caracteristicas;
	private Set<String> linkImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String, String>> opinioes;
	
	private double mediaNotas;

	public ProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		
		this.caracteristicas = produto.mapCaracteristicas(DetalhesProdutosCaracteristica :: new);
		
		this.linkImagens = produto.mapeiaImagem(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getPerguntas());
		this.opinioes = produto.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		Set<Integer> notas = produto.mapeiaOpinioes(opiniao -> opiniao.getNota());
	    IntStream preparaNotas = notas.stream().mapToInt(nota -> nota);
		OptionalDouble average = preparaNotas.average();
		if(average.isPresent()) {
			this.mediaNotas = average.getAsDouble();
		}
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

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}	
}
