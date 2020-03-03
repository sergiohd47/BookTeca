package dad.web.bookteca.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private UsuarioAuthProvider authenticationProvider;
		
	@Order(1)
	@Configuration
	public class InitSession extends WebSecurityConfigurerAdapter {
		
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
			http.authorizeRequests().antMatchers("/sesionCerrada").permitAll();
			
			http.authorizeRequests().antMatchers("/vendor/**").permitAll();
			http.authorizeRequests().antMatchers("/css/**").permitAll();
			http.authorizeRequests().antMatchers("/imagenes/**").permitAll();
			http.authorizeRequests().antMatchers("/js/**").permitAll();
				
			//PARTE PRIVADA
			http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("USER","ADMIN");
			http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("USER","ADMIN");
			http.authorizeRequests().antMatchers("/inicio").hasAnyRole("USER","ADMIN");
			
			// ROL USUARIO
			http.authorizeRequests().antMatchers("/editarPerfil").hasRole("USER");
			http.authorizeRequests().antMatchers("/perfilEditado").hasRole("USER");
			//http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroDevuelto").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaDevuelta").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoDesocupado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaDesocupada").hasRole("USER");

			// ROL ADMINISTRADOR
			//http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirLibro").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/libroAñadido").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirRevista").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/revistaAñadida").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirSalaTrabajoGrupo").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/salaAñadida").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirEquipoInformatico").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/equipoAñadido").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/administrarUsuarios").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/busquedaUsuarios").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/usuarioAdministrado").hasRole("ADMIN");
	
			// -- LOGIN
			http.formLogin().loginPage("/iniciarSesion");
			http.formLogin().usernameParameter("nombreUsuario");
			http.formLogin().passwordParameter("contrasenya");
			http.formLogin().defaultSuccessUrl("/sesionIniciada");
			http.formLogin().failureUrl("/registro");
			
			// -- LOGOUT
			//http.logout().logoutUrl("/sesionCerrada");
			http.logout().logoutSuccessUrl("/");
			
			// -- CSRF
			//http.csrf().disable();
			
		}
	
		@Override    
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
		}
	}
	
	@Order(2)
	@Configuration
	public class RegisterSession extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			//PARTE PUBLICA
			http.authorizeRequests().antMatchers("/").permitAll();
			http.authorizeRequests().antMatchers("/iniciarSesionTrasRegistro").permitAll();
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
			http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("USER","ADMIN");
			http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("USER","ADMIN");
			http.authorizeRequests().antMatchers("/inicio").hasAnyRole("USER","ADMIN");
			
			// ROL USUARIO
			http.authorizeRequests().antMatchers("/editarPerfil").hasRole("USER");
			http.authorizeRequests().antMatchers("/perfilEditado").hasRole("USER");
			//http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroDevuelto").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaDevuelta").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoDesocupado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaDesocupada").hasRole("USER");
			
			// ROL ADMINISTRADOR
			//http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirLibro").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/libroAñadido").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirRevista").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/revistaAñadida").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirSalaTrabajoGrupo").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/salaAñadida").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/añadirEquipoInformatico").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/equipoAñadido").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/administrarUsuarios").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/busquedaUsuarios").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/usuarioAdministrado").hasRole("ADMIN");

			// -- LOGIN
			http.formLogin().loginPage("/iniciarSesionTrasRegistro");
			http.formLogin().usernameParameter("nombreUsuario");
			http.formLogin().passwordParameter("contrasenya");
			http.formLogin().defaultSuccessUrl("/sesionIniciada");
			//http.formLogin().failureUrl("/registro");
			
			// -- LOGOUT
			//http.logout().logoutUrl("/sesionCerrada");
			http.logout().logoutSuccessUrl("/");
			
			// -- CSRF
			//http.csrf().disable();
			
		}
		
		@Override    
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
		}
	}
}
