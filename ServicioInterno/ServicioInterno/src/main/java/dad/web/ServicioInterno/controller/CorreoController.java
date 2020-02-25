package dad.web.ServicioInterno.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dad.web.ServicioInterno.clases.Usuario;
import dad.web.ServicioInterno.clases.Email;

@RestController
@RequestMapping("/correo")
public class CorreoController {
	
	@GetMapping(value= "/libro/{id}")
	public String emailLibor(@PathVariable Usuario usuario) {
		String mensaje = "";
		Email email = new Email(usuario);
		mensaje = email.comprobarLibros();
		return mensaje;
	}
	@GetMapping(value= "/revista/{id}")
	public String emailRevista(@PathVariable Usuario usuario) {
		String mensaje = "";
		Email email = new Email(usuario);
		mensaje = email.comprobarRevistas();
		return mensaje;
	}
	@GetMapping(value= "/sala/{id}")
	public String emailSala(@PathVariable Usuario usuario) {
		String mensaje = "";
		Email email = new Email(usuario);
		mensaje = email.comprobarSala();
		return mensaje;
	}
	@GetMapping(value= "/equipo/{id}")
	public String emailEquipo(@PathVariable Usuario usuario) {
		String mensaje = "";
		Email email = new Email(usuario);
		mensaje = email.comprobarEquipo();
		return mensaje;
	}
}

