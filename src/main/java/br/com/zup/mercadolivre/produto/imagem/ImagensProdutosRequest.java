package br.com.zup.mercadolivre.produto.imagem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class ImagensProdutosRequest {
	
	//@Size(min = 1)
	//@NotNull(message = "Campo não deve ser nulo")
	private List<MultipartFile> imagens = new ArrayList<>();

	public void setImagens(List<MultipartFile> imagens) {
		
		this.imagens = imagens;
	
	}

	public List<MultipartFile> getImages() {
		// TODO Auto-generated method stub
		return imagens;
	}
	
}
