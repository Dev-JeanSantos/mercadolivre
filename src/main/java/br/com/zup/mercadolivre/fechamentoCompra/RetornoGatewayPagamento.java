package br.com.zup.mercadolivre.fechamentoCompra;

public interface RetornoGatewayPagamento {
	
	//Uma Nova Transação baseado no gateway definido
	public Transacao toTransacao(Compra compra);
}
