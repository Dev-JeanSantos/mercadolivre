package br.com.zup.mercadolivre.fechamentoCompra;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class NovaCompraRequest {
	
	@Positive
	int quantidade;
	@NotNull
	Long idProduto;
	@NotNull
	Gateway gateway;
	
	
	public NovaCompraRequest(@Positive int quantidade, Long idProduto, Gateway gateway) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
		
	}

	@Override
	public String toString() {
		return "NovaCompraRequest [quantidade=" + quantidade + ", idProduto=" + idProduto + "]";
	}

	public Long getIdProduto() {
		// TODO Auto-generated method stub
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Gateway getGateway() {
		return gateway;
	}
}