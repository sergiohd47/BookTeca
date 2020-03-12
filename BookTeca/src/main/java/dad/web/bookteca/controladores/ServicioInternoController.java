package dad.web.bookteca.controladores;



import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller	
public class ServicioInternoController {

	private static final String url = "https://localhost/8442/getMensaje/";

	
	@PostMapping("/libroReservado")
	public String getCorreoLibro(@RequestParam String emailUsuario, @RequestParam long idLibro, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				JSONObject reserva = new JSONObject();
				reserva.put("correo", emailUsuario);
				reserva.put("idLibro", idLibro);
				ResponseEntity<String> response = rest.getForEntity(url + "/libro/" + reserva, String.class);
				
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	@PostMapping("/revistaReservada")
	public String getAvisoRevista(@RequestParam String emailUsuario, @RequestParam long idRevista, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				JSONObject reserva = new JSONObject();
				reserva.put("correo", emailUsuario);
				reserva.put("idRevista", idRevista);
				ResponseEntity<String> response = rest.getForEntity(url + "/revista/" + reserva, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	@PostMapping("/equipoReservado")
	public String getAvisoEquipo(@RequestParam String emailUsuario, @RequestParam long idEquipo, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				JSONObject reserva = new JSONObject();
				reserva.put("correo", emailUsuario);
				reserva.put("idEquipo", idEquipo);
				ResponseEntity<String> response = rest.getForEntity(url + "/equipo/" + reserva, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	@PostMapping("/salaReservada")
	public String getAvisoSala(@RequestParam String emailUsuario, @RequestParam long idSala, HttpServletResponse respuesta) {
		RestTemplate rest = new RestTemplate();
		String mensaje = "";
		try {
				JSONObject reserva = new JSONObject();
				reserva.put("correo", emailUsuario);
				reserva.put("idEquipo", idSala);
				ResponseEntity<String> response = rest.getForEntity(url + "/sala/" + reserva, String.class);
				if (response.getStatusCode().equals(HttpStatus.OK)) {
					mensaje = response.toString();
				}
		}catch (Exception error){
			
		}
		
		return mensaje;
	}
	
	
}
