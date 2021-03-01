package br.com.zup.mercadolivre.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class SenhaLimpa {
	
	private String senha;

	public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
		
		Assert.hasLength(senha, "senha não deve está vazio ou em branco");
		Assert.isTrue(StringUtils.hasLength(senha), "senha deve ser preenchido");
		this.senha = senha;
	}

	public  String hash() {
		
		return new BCryptPasswordEncoder().encode(senha);
	}
	
	
}
