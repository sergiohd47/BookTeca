package dad.web.bookteca.seguridad;

import java.util.ArrayList;
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
	
	private static List<Usuario> listaUsuarios = new ArrayList<>();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String correo = authentication.getName();
		Usuario usuario = usuarios.findByEmail(correo);
		if(usuario == null)
			throw new BadCredentialsException("Usuario no encontrado");
		String contraseña = (String) authentication.getCredentials();
		if(!(new BCryptPasswordEncoder().matches(contraseña,usuario.getContrasenya())))
			throw new BadCredentialsException("Contraseña incorrecta");
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(usuario.getRole()));
        return new UsernamePasswordAuthenticationToken(correo,contraseña,grantedAuthorities);
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
