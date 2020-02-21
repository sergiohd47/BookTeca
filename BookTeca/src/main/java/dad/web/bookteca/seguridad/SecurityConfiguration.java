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
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//MIRAR DESPUES
	}

}
