package dad.web.ServicioInterno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dad.web.ServicioInterno.clases.Usuario;
import dad.web.ServicioInterno.clases.Libro;
import dad.web.ServicioInterno.clases.Revista;
import dad.web.ServicioInterno.clases.SalaTrabajoGrupo;
import dad.web.ServicioInterno.clases.EquipoInformatico;
import dad.web.ServicioInterno.clases.Email;
import dad.web.ServicioInterno.basedatos.LibroRepository;
import dad.web.ServicioInterno.basedatos.RevistaRepository;
import dad.web.ServicioInterno.basedatos.SalaTrabajoGrupoRepository;
import dad.web.ServicioInterno.basedatos.EquipoInformaticoRepository;
import dad.web.ServicioInterno.basedatos.UsuarioRepository;


@RestController
public class CorreoController {
	
	@Autowired
	private LibroRepository libros;

	@Autowired
	private RevistaRepository revistas;
	
	@Autowired
	private SalaTrabajoGrupoRepository salasTrabajoGrupo;
	
	@Autowired
	private EquipoInformaticoRepository equiposInformaticos;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@GetMapping(value= "/getMensaje/{correo}/{idLibro}")
	public String emailLibro(@PathVariable String correo, @PathVariable int idLibro) {
		
		Libro libro = libros.findById(idLibro);
		Usuario usuario = usuarios.findByEmail(correo);
		Email email = new Email(usuario);
		
		if(usuario.reservarLibro(libro)) {
			libros.save(libro);
			usuarios.save(usuario);
			email.libroReservado(libro);
		} else {
			email.mensajeError();
		}
		
		return email.getMensaje();
	}
	
	@GetMapping(value= "/revista/{correo}/{idRevista}")
	public String emailRevista(@PathVariable String correo, @PathVariable int idRevista) {
		
		Revista revista = revistas.findById(idRevista);
		Usuario usuario = usuarios.findByEmail(correo);
		Email email = new Email(usuario);

		if(usuario.reservarRevista(revista)) {
			revistas.save(revista);
			usuarios.save(usuario);
			email.revistaReservada(revista);
		} else {
			email.mensajeError();
		}
		
		return email.getMensaje();
	}
	
	@GetMapping(value= "/sala/{correo}/{idSala}")
	public String emailSala(@PathVariable String correo, @PathVariable int idSala) {
		
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		Usuario usuario = usuarios.findByEmail(correo);
		Email email = new Email(usuario);

		if(usuario.reservarSalaTrabajoGrupo(sala)) {
			salasTrabajoGrupo.save(sala);
			usuarios.save(usuario);
			email.salaReservada(sala);
		} else {
			email.mensajeError();
		}
		
		return email.getMensaje();
	}
	@GetMapping(value= "/equipo/{correo}/{idEquipo}")
	public String emailEquipo(@PathVariable String correo, @PathVariable int idEquipo) {
		
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		Usuario usuario = usuarios.findByEmail(correo);
		Email email = new Email(usuario);

		if(usuario.reservarPuestoInformatico(equipo)) {
			equiposInformaticos.save(equipo);
			usuarios.save(usuario);
			email.equipoReservado(equipo);
		} else {
			email.mensajeError();
		}
		
		return email.getMensaje();
	}
}
