package br.com.zup.mercadolivre.usuario;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank @Size String senha;
	private @Future @NotNull LocalDate dataInscricao;
	
	/**
	 * 
	 * @param email string no formato email
	 * @param senha string com texto limpo
	 * @param dataInscricao 
	 */
	
	public Usuario(
			@Email @NotBlank String email, 
			@NotBlank @Size SenhaLimpa senhaLimpa, 
			@Future @NotNull LocalDate dataInscricao) {
				
				//PROGRAMAÇÃO DEFENSIVA
				Assert.isTrue(StringUtils.hasLength(email), "email deve ser preenchido");
				Assert.notNull(senhaLimpa, "O objeto senha limpa não deve ser nulo");
				this.email = email;
				this.senha = senhaLimpa.hash();
				this.dataInscricao = dataInscricao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + "]";
	}
	
}
