package dad.web.bookteca.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
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
		http.authorizeRequests().anyRequest().authenticated();
		//USUARIO
		http.authorizeRequests().antMatchers("/sesionIniciada").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/inicio").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/iniciarSesionTrasRegistro").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/miPerfil").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/editarPerfil").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/perfilEditado").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/buscadorLibros").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/busquedaLibros").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/buscadorRevistas").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/busquedaRevistas").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/libroReservado").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/revistaReservada").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/libroDevuelto").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/revistaDevuelta").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/reservaSalaTrabajoGrupo").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/reservaEquipoInformatico").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/equipoReservado").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/salaReservada").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/equipoDesocupado").hasRole("USUARIO");
		http.authorizeRequests().antMatchers("/salaDesocupada").hasRole("USUARIO");
		
		//ADMIN
		http.authorizeRequests().antMatchers("/sesionIniciada").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/inicio").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/miPerfil").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirLibro").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/libroAñadido").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirRevista").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/revistaAñadida").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirSalaTrabajoGrupo").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/salaAñadida").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirEquipoInformatico").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/equipoAñadido").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/administrarUsuarios").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/busquedaUsuarios").hasRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/usuarioAdministrado").hasRole("ADMINISTRADOR");

		
		//LOGIN
		http.formLogin().loginPage("/iniciarSesion");
		http.formLogin().usernameParameter("nombreUsuario");
		http.formLogin().passwordParameter("contrasenya");
		http.formLogin().defaultSuccessUrl("/sesionIniciada");
		http.formLogin().failureUrl("/iniciarSesion");
		
		//LOGOUT
		http.logout().logoutUrl("/sesionCerrada");
		http.logout().logoutSuccessUrl("/");
		
		//CSRF
		//http.csrf().disable();
	}

}
