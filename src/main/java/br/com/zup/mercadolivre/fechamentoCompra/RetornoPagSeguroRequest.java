package br.com.zup.mercadolivre.fechamentoCompra;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento{
	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagSeguro status;
	
	public RetornoPagSeguroRequest(String idTransacao, StatusRetornoPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
		System.out.println("passei aqui");
	}

	@Override
	public String toString() {
		return "RetornoPagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toTransacao(Compra compra) {
		// TODO Auto-generated method stub
		return new Transacao(status.normaliza(), idTransacao, compra);
	}

	
}
