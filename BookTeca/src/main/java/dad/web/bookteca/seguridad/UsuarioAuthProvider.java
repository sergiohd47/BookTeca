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
	
	@Autowired
	private UsuarioService servicioUsuario;
	
	//public static List<UsuarioRol> listaUsuariosRol = new ArrayList<>();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        if ("externaluser".equals(email) && "pass".equals(password))
            return new UsernamePasswordAuthenticationToken(email, password,Collections.emptyList());
        else
            throw new BadCredentialsException("External system authentication failed");
        
		/*
		String email = authentication.getName();
        Object credentials = authentication.getCredentials();
        if(!(credentials instanceof String))
            return null;
        String password = credentials.toString();
        Optional<UsuarioRol> usuarioRolOpt = listaUsuariosRol.stream().filter(u -> u.match(email,password)).findFirst();
        if(!usuarioRolOpt.isPresent())
            throw new BadCredentialsException("Autenticacion fallida");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(usuarioRolOpt.get().rol));
        return new UsernamePasswordAuthenticationToken(email,password,grantedAuthorities);
        */
		/*
		String correo = authentication.getName();
		Usuario usuario = usuarios.findByEmail(correo);
		if(usuario == null)
			throw new BadCredentialsException("Usuario no encontrado");
		String contraseña = (String) authentication.getCredentials();
		if(!(new BCryptPasswordEncoder().matches(contraseña,usuario.getContrasenya())))
			throw new BadCredentialsException("Contraseña incorrecta");
		servicioUsuario.setUsuarioLogueado(usuario);
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(usuario.getRole()));
        return new UsernamePasswordAuthenticationToken(correo,contraseña,grantedAuthorities);
        */
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
