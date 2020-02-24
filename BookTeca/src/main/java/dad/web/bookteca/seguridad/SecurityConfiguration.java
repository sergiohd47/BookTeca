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
				auth.inMemoryAuthentication().withUser(usuario.getEmail()).password(usuario.getContrasenya()).roles("USUARIO");
			else
				auth.inMemoryAuthentication().withUser(usuario.getEmail()).password(usuario.getContrasenya()).roles("ADMINISTRADOR");
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
		http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/inicio").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/iniciarSesionTrasRegistro").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/editarPerfil").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/perfilEditado").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/buscadorLibros").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/busquedaLibros").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/buscadorRevistas").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/busquedaRevistas").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/libroReservado").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/revistaReservada").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/libroDevuelto").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/revistaDevuelta").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/reservaSalaTrabajoGrupo").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/reservaEquipoInformatico").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/equipoReservado").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/salaReservada").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/equipoDesocupado").hasAnyRole("USUARIO");
		http.authorizeRequests().antMatchers("/salaDesocupada").hasAnyRole("USUARIO");
		
		// -- ADMINISTRADOR
		http.authorizeRequests().antMatchers("/sesionIniciada").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/inicio").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/miPerfil").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/sesionCerrada").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirLibro").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/libroAñadido").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirRevista").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/revistaAñadida").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirSalaTrabajoGrupo").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/salaAñadida").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/añadirEquipoInformatico").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/equipoAñadido").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/administrarUsuarios").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/busquedaUsuarios").hasAnyRole("ADMINISTRADOR");
		http.authorizeRequests().antMatchers("/usuarioAdministrado").hasAnyRole("ADMINISTRADOR");

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
