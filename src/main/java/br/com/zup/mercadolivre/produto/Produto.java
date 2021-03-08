package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.perguntas.Pergunta;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicaProduto;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @PositiveOrZero int quantidade;
	private @Positive BigDecimal valor;
	private @NotBlank @Length(max = 1000) String descricao;
	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull 
	@Valid 
	@ManyToOne
	private Usuario usuario;

	@Size(min = 3)
	@Valid
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();
	
	@Deprecated
	public Produto() {
		
	}
	
	public Produto(@NotBlank String nome, @PositiveOrZero int quantidade, @Positive BigDecimal valor,
			@NotBlank @Length(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario usuario, 
			@Valid @Size(min = 3) Collection<CaracteristicaRequest> caracteristicas) {

		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		
		this.caracteristicas.addAll(caracteristicas.stream().map
				(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
		Assert.isTrue(this.caracteristicas.size() >= 3, "Cadastre no minimo 3 caracteristicas");
		
	}
	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaProdutos(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
			.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", valor=" + valor
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", usuario=" + usuario
				+ ", caracteristicas=" + caracteristicas + ", imagens=" + imagens + "]";
	}

	public boolean pertenceAoUsuario(Usuario possivelUsuario) {
		
		return this.usuario.equals(possivelUsuario);
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		// TODO Auto-generated method stub
		return this.caracteristicas;
	}

	public <T> Set<T> mapCaracteristicas(
			Function<CaracteristicaProduto, T> funcaoMapear) {

		return this.caracteristicas.stream().map(funcaoMapear).
				collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaImagem(
			Function<ImagemProduto, T> funcaoMapeaLink) {
		return this.imagens.stream().map(funcaoMapeaLink).
				collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(
			Function<Pergunta, T> funcaoMapeaPergunta) {
		return this.perguntas.stream().map(funcaoMapeaPergunta).
				collect(Collectors.toCollection(TreeSet::new));
	}
	
	
}
