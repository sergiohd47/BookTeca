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
import dad.web.bookteca.servicios.UsuarioService;

@Component
public class UsuarioAuthProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioRepository usuarios;
	
	
	//public static List<UsuarioRol> listaUsuariosRol = new ArrayList<>();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName(); //getEmail
		Usuario usuario = usuarios.findByEmail(email); 
		if (usuario == null) {
			throw new BadCredentialsException("User not found");
		}
			 
		String password = (String) authentication.getCredentials();
		/*if (!new BCryptPasswordEncoder().matches(password, usuario.getContrasenya())) {
			 throw new BadCredentialsException("Wrong password");	
		}*/
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRole()));
		return new UsernamePasswordAuthenticationToken(usuario.getEmail(), password, roles);
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	/*
	public static class UsuarioRol {
		private String email;
		private String contrasenya;
		private String rol;
		
		public UsuarioRol(String email, String contrasenya, String rol) {
			this.email = email;
			this.contrasenya = contrasenya;
			this.rol = rol;
		}
		
		public boolean match(String email, String contrasenya) {
            return (this.email.equals(email) && this.contrasenya.equals(contrasenya));
        }
	}
	*/
}
