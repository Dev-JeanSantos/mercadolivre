package br.com.zup.mercadolivre.utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer{

	@Override
	public void send(@NotBlank String corpo, @NotBlank String assunto,
				@NotBlank String nomeOrigem,@NotBlank @Email String emailOrigem,@NotBlank @Email String emailDestino) {
		System.out.println(corpo);
		System.out.println(assunto);
		System.out.println(nomeOrigem);
		System.out.println(emailOrigem);
		System.out.println(emailDestino);
	}
	
	
}
