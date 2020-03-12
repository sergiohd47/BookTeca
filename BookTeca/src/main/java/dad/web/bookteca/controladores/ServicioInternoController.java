package dad.web.bookteca.controladores;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import dad.web.bookteca.basedatos.EquipoInformaticoRepository;
import dad.web.bookteca.basedatos.LibroRepository;
import dad.web.bookteca.basedatos.RevistaRepository;
import dad.web.bookteca.basedatos.SalaTrabajoGrupoRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;

import org.json.*;

@RestController
public class ServicioInternoController {

	private static final String URL = "https://localhost:8443/";
	
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
	
	@PostMapping("/libroReservado")
	public String getCorreoLibro(@RequestParam long idLibro, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		/*try {
			JSONObject reserva = new JSONObject();
			reserva.put("correo", usuario.getEmail());
			reserva.put("idLibro", idLibro);
			ResponseEntity<String> response = rest.getForEntity(URL + "/libro/" + reserva, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK))
				mensaje = response.toString();
			
		}catch (Exception error){
			
		}*/
		Optional<Libro> libroOpt = libros.findById((Long)idLibro);
		if(libroOpt.isPresent()) {
			Libro libro = libroOpt.get();
			ResponseEntity<String> response = rest.getForEntity(URL + "/libroReservado/" + idLibro + "::" + 
					libro.getIdUsuario().getEmail(), String.class);
			if(response.getStatusCode().equals(HttpStatus.OK))
				mensaje+=response.toString();
		}
		return mensaje;
	}
	
	@PostMapping("/revistaReservada")
	public String getAvisoRevista(@RequestParam long idRevista, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		Revista revista = revistas.findById(idRevista);
		String emailUsuario = revista.getIdUsuario().getEmail();
		try {
			JSONObject reserva = new JSONObject();
			reserva.put("correo", emailUsuario);
			reserva.put("idRevista", idRevista);
			ResponseEntity<String> response = rest.getForEntity(URL + "/revista/" + reserva, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK))
				mensaje = response.toString();
		}catch (Exception error){
			
		}	
		return mensaje;
	}
	
	@PostMapping("/equipoReservado")
	public String getAvisoEquipo(@RequestParam long idEquipo, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		String emailUsuario = equipo.getIdUsuario().getEmail();
		try {
			JSONObject reserva = new JSONObject();
			reserva.put("correo", emailUsuario);
			reserva.put("idEquipo", idEquipo);
			ResponseEntity<String> response = rest.getForEntity(URL + "/equipo/" + reserva, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK))
				mensaje = response.toString();
		}catch (Exception error){
			
		}
		return mensaje;
	}
	
	@PostMapping("/salaReservada")
	public String getAvisoSala(@RequestParam long idSala, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		String emailUsuario = sala.getIdUsuario().getEmail();
		try {
			JSONObject reserva = new JSONObject();
			reserva.put("correo", emailUsuario);
			reserva.put("idSala", idSala);
			ResponseEntity<String> response = rest.getForEntity(URL + "/sala/" + reserva, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK))
				mensaje = response.toString();
		}catch (Exception error){
			
		}
		return mensaje;
	}
	
	
}
