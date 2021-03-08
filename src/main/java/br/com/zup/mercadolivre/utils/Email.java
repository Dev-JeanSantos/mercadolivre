package br.com.zup.mercadolivre.utils;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.perguntas.Pergunta;

@Service
public class Email {
	
	@Autowired
	Mailer mailer;
	
	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		
		mailer.send("<html>....</html>","Nova Pergunta.... ","perguntas@mercadolivre.com" ,pergunta.getUsuarioInteressado().getEmail(),
				pergunta.getDonoProduto().getEmail());
		
	}
	
}
