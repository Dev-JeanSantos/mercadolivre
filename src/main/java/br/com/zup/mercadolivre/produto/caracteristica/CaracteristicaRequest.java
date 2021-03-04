package br.com.zup.mercadolivre.produto.caracteristica;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.produto.Produto;

public class CaracteristicaRequest {

	@NotBlank
	private String nomeCaracteristica;
	@NotBlank
	private String descricaoCaracteristica;
	
	public CaracteristicaRequest(@NotBlank String nomeCaracteristica, @NotBlank String descricaoCaracteristica) {
		this.nomeCaracteristica = nomeCaracteristica;
		this.descricaoCaracteristica = descricaoCaracteristica;
	}

	public String getNomeCaracteristica() {
		return nomeCaracteristica;
	}

	public String getDescricaoCaracteristica() {
		return descricaoCaracteristica;
	}

	@Override
	public String toString() {
		return "CaracteristicaRequest [nomeCaracteristica=" + nomeCaracteristica + ", descricaoCaracteristica="
				+ descricaoCaracteristica + "]";
	}

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		// TODO Auto-generated method stub
		return new CaracteristicaProduto(nomeCaracteristica, descricaoCaracteristica,produto);
	}	
	
}
