package br.com.zup.mercadolivre.datelheProduto;

import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicaProduto;

public class DetalhesProdutosCaracteristica {

	private String nome;
	private String descricao;

	public DetalhesProdutosCaracteristica(CaracteristicaProduto caracteristicas) {
		this.nome = caracteristicas.getNome();
		this.descricao = caracteristicas.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
