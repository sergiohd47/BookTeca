package dad.web.bookteca.servicios;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Usuario;

@Component
public class UsuarioService {
	
	public boolean reservarLibro(Usuario usuario,Libro l) {
		if (l.isDisponible()){
			if (usuario.getLibrosReservados().size() < 3) {
				usuario.getLibrosReservados().add(l);
				Calendar calendar = Calendar.getInstance();
				Date inicio = Date.valueOf(java.time.LocalDate.now());
				calendar.setTime(inicio); 
			    calendar.add(Calendar.DAY_OF_YEAR , 7);
				Date fin = (Date) calendar.getTime();
				l.reservar(usuario, inicio, fin);
				return true;
			}
		}
		return false;
	}
}
