package br.com.zup.mercadolivre.handler.exceptions;

public class MailSenderException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MailSenderException(String message){
        super(message);
    }
}
