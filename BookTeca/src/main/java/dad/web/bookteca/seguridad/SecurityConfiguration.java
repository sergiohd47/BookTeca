package dad.web.bookteca.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioAuthProvider authenticationProvider;
		
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//PARTE PUBLICA
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/iniciarSesion").permitAll();
		http.authorizeRequests().antMatchers("/registro").permitAll();
		http.authorizeRequests().antMatchers("/buscadorLibros").permitAll();
		http.authorizeRequests().antMatchers("/busquedaLibros").permitAll();
		http.authorizeRequests().antMatchers("/buscadorRevistas").permitAll();
		http.authorizeRequests().antMatchers("/busquedaRevistas").permitAll();
		http.authorizeRequests().antMatchers("/reservaSalaTrabajoGrupo").permitAll();
		http.authorizeRequests().antMatchers("/reservaEquipoInformatico").permitAll();
		
		http.authorizeRequests().antMatchers("/vendor/**").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/imagenes/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
			
		//PARTE PRIVADA
		http.authorizeRequests().antMatchers("/sesionIniciada").authenticated();
		http.authorizeRequests().antMatchers("/inicio").authenticated();
		http.authorizeRequests().antMatchers("/iniciarSesionTrasRegistro").authenticated();
		http.authorizeRequests().antMatchers("/miPerfil").authenticated();
		http.authorizeRequests().antMatchers("/editarPerfil").authenticated();
		http.authorizeRequests().antMatchers("/perfilEditado").authenticated();
		http.authorizeRequests().antMatchers("/sesionCerrada").authenticated();
		//http.authorizeRequests().antMatchers("/buscadorLibros").authenticated();
		//http.authorizeRequests().antMatchers("/busquedaLibros").authenticated();
		//http.authorizeRequests().antMatchers("/buscadorRevistas").authenticated();
		//http.authorizeRequests().antMatchers("/busquedaRevistas").authenticated();
		http.authorizeRequests().antMatchers("/libroReservado").authenticated();
		http.authorizeRequests().antMatchers("/revistaReservada").authenticated();
		http.authorizeRequests().antMatchers("/libroDevuelto").authenticated();
		http.authorizeRequests().antMatchers("/revistaDevuelta").authenticated();
		//http.authorizeRequests().antMatchers("/reservaSalaTrabajoGrupo").authenticated();
		//http.authorizeRequests().antMatchers("/reservaEquipoInformatico").authenticated();
		http.authorizeRequests().antMatchers("/equipoReservado").authenticated();
		http.authorizeRequests().antMatchers("/salaReservada").authenticated();
		http.authorizeRequests().antMatchers("/equipoDesocupado").authenticated();
		http.authorizeRequests().antMatchers("/salaDesocupada").authenticated();
		http.authorizeRequests().antMatchers("/sesionIniciada").authenticated();
		//http.authorizeRequests().antMatchers("/inicio").authenticated();
		//http.authorizeRequests().antMatchers("/miPerfil").authenticated();
		//http.authorizeRequests().antMatchers("/sesionCerrada").authenticated();
		http.authorizeRequests().antMatchers("/añadirLibro").authenticated();
		http.authorizeRequests().antMatchers("/libroAñadido").authenticated();
		http.authorizeRequests().antMatchers("/añadirRevista").authenticated();
		http.authorizeRequests().antMatchers("/revistaAñadida").authenticated();
		http.authorizeRequests().antMatchers("/añadirSalaTrabajoGrupo").authenticated();
		http.authorizeRequests().antMatchers("/salaAñadida").authenticated();
		http.authorizeRequests().antMatchers("/añadirEquipoInformatico").authenticated();
		http.authorizeRequests().antMatchers("/equipoAñadido").authenticated();
		http.authorizeRequests().antMatchers("/administrarUsuarios").authenticated();
		http.authorizeRequests().antMatchers("/busquedaUsuarios").authenticated();
		http.authorizeRequests().antMatchers("/usuarioAdministrado").authenticated();
		
		
		// ROL USUARIO
		http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/inicio").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/iniciarSesionTrasRegistro").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/editarPerfil").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/perfilEditado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/buscadorLibros").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/busquedaLibros").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/buscadorRevistas").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/busquedaRevistas").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/libroReservado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/revistaReservada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/libroDevuelto").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/revistaDevuelta").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/reservaSalaTrabajoGrupo").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/reservaEquipoInformatico").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/equipoReservado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/salaReservada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/equipoDesocupado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/salaDesocupada").hasAnyRole("USER");
		
		// ROL ADMINISTRADOR
		http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/inicio").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/añadirLibro").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/libroAñadido").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/añadirRevista").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/revistaAñadida").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/añadirSalaTrabajoGrupo").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/salaAñadida").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/añadirEquipoInformatico").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/equipoAñadido").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/administrarUsuarios").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/busquedaUsuarios").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/usuarioAdministrado").hasAnyRole("ADMIN");

		// -- LOGIN
		http.formLogin().loginPage("/iniciarSesion");
		http.formLogin().usernameParameter("nombreUsuario");
		http.formLogin().passwordParameter("contrasenya");
		http.formLogin().defaultSuccessUrl("/sesionIniciada");
		http.formLogin().failureUrl("/iniciarSesion");
		
		// -- LOGOUT
		http.logout().logoutUrl("/sesionCerrada");
		http.logout().logoutSuccessUrl("/");
		
		// -- CSRF
		//http.csrf().disable();
		
	}
	
	@Override    
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
}
