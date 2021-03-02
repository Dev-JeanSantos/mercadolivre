package br.com.zup.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.handler.exceptions.validations.UniqueValue;

public class UsuarioRequest {
	
	@Email(message = "O formato do email está inválido")
	@NotBlank(message = "O campo email não deve ser vazio")
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank(message = "O campo email não deve ser vazio")
	@Size(min = 6, message = "O minimo de caracteres da senha é de 6 digitos")
	private String senha;
	
	public UsuarioRequest(@Email @NotBlank String email, 
			@NotBlank @Size(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		
		return new Usuario(email, new SenhaLimpa(senha));
		
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	
	
}
