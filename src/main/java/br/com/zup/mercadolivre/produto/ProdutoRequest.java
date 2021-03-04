package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.handler.exceptions.validations.ExistsId;
import br.com.zup.mercadolivre.handler.exceptions.validations.UniqueValue;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zup.mercadolivre.usuario.Usuario;

public class ProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class,fieldName = "nome", message = "Já existe um produto com esse nome")
	private String nome;

	@NotNull
	@PositiveOrZero
	private int quantidade;

	@Positive
	@NotNull
	private BigDecimal valor;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;

	@Valid
	@Size(min = 3)
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest(@NotBlank String nome, @PositiveOrZero int quantidade, @Positive BigDecimal valor,
			@NotBlank @Length(max = 1000) String descricao, Long categoriaId,
			List<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto toModel(EntityManager manager, Usuario usuario) {

		Categoria categoria = manager.find(Categoria.class, categoriaId);

		return new Produto(nome, quantidade, valor, descricao, categoria, usuario, caracteristicas);
	}

	@Override
	public String toString() {
		return "ProdutoRequest [nome=" + nome + ", quantidade=" + quantidade + ", valor=" + valor + ", descricao="
				+ descricao + ", categoriaId=" + categoriaId + ", caracteristicas=" + caracteristicas + "]";
	}

	public Set<String> buscarCaracteristicasIguais() {
        HashSet<String> nomesIguais  = new HashSet<>();
        HashSet<String> resultados  = new HashSet<>();

        for( CaracteristicaRequest caracteristica : caracteristicas){
            String nome = caracteristica.getNomeCaracteristica();
            if(!nomesIguais.add(nome)){
                  resultados.add(nome);
              }
        }
        return resultados;
    }
	
    
}
