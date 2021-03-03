package br.com.zup.mercadolivre.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@Component
public class AuthUtils {

    @Autowired
    private UsuarioRepository repository;

    public Usuario checkUser() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = repository.findByEmail(a.getName());
        if(usuario == null){
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return usuario;
    }
}