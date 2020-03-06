package dad.web.bookteca.controladores;



import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller	
public class ServicioInternoController {

	private static final String url = "https://localhost/8443/getMensaje/";
	
	@GetMapping("/correo/libro")
	public String getAvisoLibro(@RequestParam String emailUsuario, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				ResponseEntity<String> response = rest.getForEntity(url + "/" + emailUsuario, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	@GetMapping("/correo/revista")
	public String getAvisoRevista(@RequestParam String emailUsuario, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				ResponseEntity<String> response = rest.getForEntity(url + "/" + emailUsuario, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	@GetMapping("/correo/equipo")
	public String getAvisoEquipo(@RequestParam String emailUsuario, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				ResponseEntity<String> response = rest.getForEntity(url + "/" + emailUsuario, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	@GetMapping("/salaReservada")
	public String getAvisoSala(@RequestParam String emailUsuario, @RequestParam long idSala, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				ResponseEntity<String> response = rest.getForEntity(url + "/" + emailUsuario + "/" + idSala, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	
}
