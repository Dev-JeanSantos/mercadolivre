package br.com.zup.mercadolivre.usuario;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"email","id"})})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank @Size String senha;
	private LocalDateTime dataInscricao = LocalDateTime.now(ZoneId.systemDefault());

	
	@Deprecated
	public Usuario() {
		
	}

	/**
	 * 
	 * @param email string no formato email
	 * @param senha string com texto limpo
	 * @param dataInscricao 
	 */
	
	public Usuario(
			@Email @NotBlank String email, 
			@NotBlank @Size SenhaLimpa senhaLimpa) {
				
				//PROGRAMAÇÃO DEFENSIVA
				Assert.isTrue(StringUtils.hasLength(email), "email deve ser preenchido");
				Assert.notNull(senhaLimpa, "O objeto senha limpa não deve ser nulo");
				this.email = email;
				this.senha = senhaLimpa.hash();
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", dataInscricao=" + dataInscricao + "]";
	}

	public Usuario get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}
}
