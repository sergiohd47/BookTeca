package dad.web.bookteca.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Usuario;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioRepository usuarios;

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override    
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		for(Usuario usuario : usuarios.findAll()) {
			if(!usuario.getAdministrador())
				auth.inMemoryAuthentication().withUser(usuario.getEmail()).password(usuario.getContrasenya()).roles("USER");
			else
				auth.inMemoryAuthentication().withUser(usuario.getEmail()).password(usuario.getContrasenya()).roles("ADMIN");
		}
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
	
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
		
		// -- USUARIO
		http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/inicio").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/iniciarSesionTrasRegistro").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/editarPerfil").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/perfilEditado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/buscadorLibros").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/busquedaLibros").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/buscadorRevistas").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/busquedaRevistas").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/libroReservado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/revistaReservada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/libroDevuelto").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/revistaDevuelta").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/reservaSalaTrabajoGrupo").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/reservaEquipoInformatico").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/equipoReservado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/salaReservada").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/equipoDesocupado").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/salaDesocupada").hasAnyRole("USER");
		
		// -- ADMINISTRADOR
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
		
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
	}
}
