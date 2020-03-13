package dad.web.ServicioInterno.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;

import dad.web.ServicioInterno.basedatos.EquipoInformaticoRepository;
import dad.web.ServicioInterno.basedatos.LibroRepository;
import dad.web.ServicioInterno.basedatos.RevistaRepository;
import dad.web.ServicioInterno.basedatos.SalaTrabajoGrupoRepository;
import dad.web.ServicioInterno.basedatos.UsuarioRepository;
import dad.web.ServicioInterno.clases.Email;

import dad.web.ServicioInterno.clases.Usuario;
import dad.web.ServicioInterno.clases.EquipoInformatico;
import dad.web.ServicioInterno.clases.Libro;
import dad.web.ServicioInterno.clases.Revista;
import dad.web.ServicioInterno.clases.SalaTrabajoGrupo;

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
	
	@PutMapping(value= "/libro/{reserva}")
	public ResponseEntity<String> emailLibro(@PathVariable JSONObject reserva) {
		Libro libro = libros.findById(reserva.getLong("idLibro"));
		Usuario usuario = usuarios.findByEmail(reserva.getString("correo"));
		Email email = new Email(usuario);
		if(usuario.reservarLibro(libro)) {
			libros.save(libro);
			usuarios.save(usuario);
			email.libroReservado(libro);
		} else {
			email.mensajeError();
			return new ResponseEntity<String>(email.getMensaje(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(email.getMensaje(), HttpStatus.OK);
	}
	
	@PutMapping(value= "/revista/{reserva}")
	public ResponseEntity<String> emailRevista(@PathVariable JSONObject reserva) {
		Revista revista = revistas.findById(reserva.getLong("idRevista"));
		Usuario usuario = usuarios.findByEmail(reserva.getString("correo"));
		Email email = new Email(usuario);
		if(usuario.reservarRevista(revista)) {
			revistas.save(revista);
			usuarios.save(usuario);
			email.revistaReservada(revista);
		} else {
			email.mensajeError();
			return new ResponseEntity<String>(email.getMensaje(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(email.getMensaje(), HttpStatus.OK);
	}
	
	@PutMapping(value= "/sala/{reserva}")
	public ResponseEntity<String> emailSala(@PathVariable JSONObject reserva) {
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(reserva.getLong("idSala"));
		Usuario usuario = usuarios.findByEmail(reserva.getString("correo"));
		Email email = new Email(usuario);
		if(usuario.reservarSalaTrabajoGrupo(sala)) {
			salasTrabajoGrupo.save(sala);
			usuarios.save(usuario);
			email.salaReservada(sala);
		} else {
			email.mensajeError();
			return new ResponseEntity<String>(email.getMensaje(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(email.getMensaje(), HttpStatus.OK);
	}
	
	@PutMapping(value= "/equipo/{reserva}")
	public ResponseEntity<String> emailEquipo(@PathVariable JSONObject reserva) {
		EquipoInformatico equipo = equiposInformaticos.findById(reserva.getLong("idEquipo"));
		Usuario usuario = usuarios.findByEmail(reserva.getString("correo"));
		Email email = new Email(usuario);
		if(usuario.reservarPuestoInformatico(equipo)) {
			equiposInformaticos.save(equipo);
			usuarios.save(usuario);
			email.equipoReservado(equipo);
		} else {
			email.mensajeError();
			return new ResponseEntity<String>(email.getMensaje(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(email.getMensaje(), HttpStatus.OK);
	}
}
