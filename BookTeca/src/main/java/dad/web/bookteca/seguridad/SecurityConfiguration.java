package dad.web.bookteca.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Usuario;
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private UsuarioAuthProvider authenticationProvider;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	
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
					
			// ROL USUARIO
			http.authorizeRequests().antMatchers("/sesionIniciada").hasRole("USER");
			http.authorizeRequests().antMatchers("/inicio").hasRole("USER");
			http.authorizeRequests().antMatchers("/miPerfil").hasRole("USER");
			http.authorizeRequests().antMatchers("/editarPerfil").hasRole("USER");
			http.authorizeRequests().antMatchers("/perfilEditado").hasRole("USER");
			http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroDevuelto").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaDevuelta").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoDesocupado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaDesocupada").hasRole("USER");

			// ROL ADMINISTRADOR
			//http.authorizeRequests().antMatchers("/sesionIniciada").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/inicio").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/miPerfil").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("ADMIN");
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
	/*
	@Order(2)
	@Configuration
	public class RegisterSession extends WebSecurityConfigurerAdapter {

		@Autowired
		private UsuarioAuthProvider authenticationProvider;
		
		@Autowired
		private UsuarioRepository usuarios;
		
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
			http.authorizeRequests().antMatchers("/sesionIniciada").authenticated();
			http.authorizeRequests().antMatchers("/inicio").authenticated();
			http.authorizeRequests().antMatchers("/miPerfil").authenticated();
			http.authorizeRequests().antMatchers("/editarPerfil").authenticated();
			http.authorizeRequests().antMatchers("/perfilEditado").authenticated();
			http.authorizeRequests().antMatchers("/sesionCerrada").authenticated();
			http.authorizeRequests().antMatchers("/libroReservado").authenticated();
			http.authorizeRequests().antMatchers("/revistaReservada").authenticated();
			http.authorizeRequests().antMatchers("/libroDevuelto").authenticated();
			http.authorizeRequests().antMatchers("/revistaDevuelta").authenticated();
			http.authorizeRequests().antMatchers("/equipoReservado").authenticated();
			http.authorizeRequests().antMatchers("/salaReservada").authenticated();
			http.authorizeRequests().antMatchers("/equipoDesocupado").authenticated();
			http.authorizeRequests().antMatchers("/salaDesocupada").authenticated();
			http.authorizeRequests().antMatchers("/sesionIniciada").authenticated();
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
			http.authorizeRequests().antMatchers("/sesionIniciada").hasRole("USER");
			http.authorizeRequests().antMatchers("/inicio").hasRole("USER");
			http.authorizeRequests().antMatchers("/miPerfil").hasRole("USER");
			http.authorizeRequests().antMatchers("/editarPerfil").hasRole("USER");
			http.authorizeRequests().antMatchers("/perfilEditado").hasRole("USER");
			http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/libroDevuelto").hasRole("USER");
			http.authorizeRequests().antMatchers("/revistaDevuelta").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoReservado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaReservada").hasRole("USER");
			http.authorizeRequests().antMatchers("/equipoDesocupado").hasRole("USER");
			http.authorizeRequests().antMatchers("/salaDesocupada").hasRole("USER");
			
			// ROL ADMINISTRADOR
			http.authorizeRequests().antMatchers("/sesionIniciada").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/inicio").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/miPerfil").hasRole("ADMIN");
			http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("ADMIN");
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
			http.formLogin().failureUrl("/registro");
			
			// -- LOGOUT
			http.logout().logoutUrl("/sesionCerrada");
			http.logout().logoutSuccessUrl("/");
			
			// -- CSRF
			//http.csrf().disable();
			
		}*/
		
		/*@Override    
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
			for(Usuario usuario : usuarios.findAll()) {
				if(usuario.getRole().equals("ADMIN"))
					auth.inMemoryAuthentication().withUser(usuario.getEmail()).password(
							encoder().encode(usuario.getContrasenya())).roles("ADMIN");
				else
					auth.inMemoryAuthentication().withUser(usuario.getEmail()).password(
							encoder().encode(usuario.getContrasenya())).roles("USER");
			}
		}
	}
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }*/
}
