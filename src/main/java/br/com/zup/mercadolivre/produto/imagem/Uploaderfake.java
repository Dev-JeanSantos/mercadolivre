package br.com.zup.mercadolivre.produto.imagem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class Uploaderfake implements Uploader{
	
	/**
	 * 
	 * @param images
	 * @return links das imagens (Uploads)
	 */
	public  Set<String> envia(List<MultipartFile> images) {
		// TODO Auto-generated method stub
		return images.stream().map(imagem -> "http://bucket.io/"+imagem.getOriginalFilename())
		.collect(Collectors.toSet());
	}

}
