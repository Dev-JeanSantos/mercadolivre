package br.com.zup.mercadolivre.handler.exceptions.validations;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.mercadolivre.produto.ProdutoRequest;

public class ProibeDuplicidadeNoNomeDaCaracteristica implements Validator {
	
	//Verifica se há herança na classe (se classe que chega é a ProdutoRequest ou é filha dela)
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		//O ProdutoRequest será meu Objeto de request
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		//Se Houver Erro para por aqui (codigo defensivo)
		if(errors.hasErrors()) {
			return;
		}
		
		//Cast
		ProdutoRequest req = (ProdutoRequest) target;
		
		Set<String> nomesIguais = req.buscarCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.reject("caracteristicas", null, "As caracteristicas estão duplicadas!" + nomesIguais);
		}
	}

}
