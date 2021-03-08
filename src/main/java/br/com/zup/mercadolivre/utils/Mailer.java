package br.com.zup.mercadolivre.utils;

public interface Mailer {

	/**
	 * 
	 * @param corpo do email
	 * @param assunto do email
	 * @param nome de quem envio o email
	 * @param email de Origem
	 * @param email de Destino
	 */
	void send(String corpo, String assunto,String nomeOrigem ,String emailOrigem, String emailDestino);

}
