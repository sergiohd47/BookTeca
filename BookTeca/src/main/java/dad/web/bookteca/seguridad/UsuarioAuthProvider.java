package dad.web.bookteca.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Usuario;

@Component
public class UsuarioAuthProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Usuario usuario = usuarios.findByEmail(authentication.getName()); 
		if(usuario == null)
			throw new BadCredentialsException("ERROR: usuario no encontrado");
		String password = (String) authentication.getCredentials();
		if(!new BCryptPasswordEncoder().matches(password,usuario.getContrasenya()))
			 throw new BadCredentialsException("ERROR: contraseña incorrecta");
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRole()));
		return new UsernamePasswordAuthenticationToken(usuario.getEmail(),password,roles);
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
