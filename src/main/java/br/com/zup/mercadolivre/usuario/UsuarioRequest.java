package br.com.zup.mercadolivre.usuario;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class UsuarioRequest {
	
	@Email(message = "O formato do email está inválido")
	@NotBlank(message = "O campo email não deve ser vazio")
	private String email;
	@NotBlank(message = "O campo email não deve ser vazio")
	@Size(min = 6, message = "O minimo de caracteres da senha é de 6 digitos")
	private String senha;
	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataInscricao;
	
	public UsuarioRequest(@Email @NotBlank String email, 
			@NotBlank @Size(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	//Unico setter da classe devido a jackson na serializar a data	
	public void setDataInscricao(LocalDate dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Usuario toModel() {
		
		return new Usuario(email, new SenhaLimpa(senha), dataInscricao);
		
	}
	
	
}
