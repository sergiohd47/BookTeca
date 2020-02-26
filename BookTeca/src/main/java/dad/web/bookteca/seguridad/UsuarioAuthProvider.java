package dad.web.bookteca.seguridad;

import java.util.ArrayList;

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
		Usuario usuario=usuarios.findByEmail(authentication.getName());
		if(usuario == null)
			 throw new BadCredentialsException("Usuario no encontrado");
		 String password = authentication.getCredentials().toString();
		 if(!new BCryptPasswordEncoder().matches(password,usuario.getContrasenya()))
			 throw new BadCredentialsException("Contraseña incorrecta");
		 ArrayList<GrantedAuthority> roles=new ArrayList<>();
		 if(usuario.getAdministrador())
			 roles.add(0,new SimpleGrantedAuthority("ADMIN"));
		 else
			roles.add(0,new SimpleGrantedAuthority("USER"));
		 return new UsernamePasswordAuthenticationToken(usuario.getEmail(),password,roles);
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
