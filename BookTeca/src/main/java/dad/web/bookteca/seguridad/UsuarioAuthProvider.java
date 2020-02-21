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
import org.springframework.stereotype.Component;

import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Usuario;


@Component
public class UsuarioAuthProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioRepository usuarios;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String email=authentication.getName();
		Usuario usuario=usuarios.findByEmail(email);
		if (usuario == null) {
			 throw new BadCredentialsException("Usuario no encontrado");
		 }
		 
		 String password = (String) authentication.getCredentials();
		 if (!password.equals(usuario.getContrasenya())) {
			 throw new BadCredentialsException("Contraseña incorrecta");
		 }
		 ArrayList<GrantedAuthority> listaRoles=new ArrayList<>();
		 if(usuario.getAdministrador()) {
			 listaRoles.add(new SimpleGrantedAuthority("ADMINISTRADOR"));
		 }else {
			 listaRoles.add(new SimpleGrantedAuthority("USUARIO"));
		 }
		 return new UsernamePasswordAuthenticationToken(usuario.getEmail(), password,listaRoles);
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
