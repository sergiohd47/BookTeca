package dad.web.ServicioInterno.clases;

import java.util.Date;

public class Email {
	

	private Usuario idUsuario;
	private String mensaje;
	
	public Email(Usuario usuario) {
		this.idUsuario = usuario;
	}
	
	public String comprobarLibros() {
		String mensaje = "";
		Date hoy = new Date();
		for(Libro libro: this.idUsuario.getLibrosReservados()) {
			if (libro.getFecFin().getTime()<hoy.getTime()) {
				if (mensaje != "") {
					mensaje = "Tiene libros sin devolver: " + libro.getNombre() + "("+ libro.getId() +")";
				}else {
					mensaje.concat(", " + libro.getNombre()+ "("+ libro.getId() +")");
				}
			}
		}
		this.mensaje=mensaje;
		return mensaje;
	}
	
	public String comprobarRevistas() {
		String mensaje = "";
		Date hoy = new Date();
		for(Revista revista: this.idUsuario.getRevistasReservadas()) {
			if (revista.getFecFin().getTime()<hoy.getTime()) {
				if (mensaje != "") {
					mensaje = "Tiene libros sin devolver: " + revista.getNombre() + "("+ revista.getId() +")";
				}else {
					mensaje.concat(", " + revista.getNombre()+ "("+ revista.getId() +")");
				}
			}
		}
		this.mensaje=mensaje;
		return mensaje;
	}
	
	public String comprobarSala() {
		String mensaje = "";
		Date hoy = new Date();
		if(idUsuario.getSalaTrabajo() != null) {
			if (idUsuario.getSalaTrabajo().getFechaReserva().getTime()<hoy.getTime()) {
				mensaje = "La reserva de la sala de trabajo ha expirado";
			}
		}
		this.mensaje=mensaje;
		return mensaje;
	}
	
	public String comprobarEquipo() {
		String mensaje = "";
		Date hoy = new Date();
		if(idUsuario.getPuestoInformatico() != null) {
			if (idUsuario.getPuestoInformatico().getFechaReserva().getTime()<hoy.getTime()) {
				mensaje = "La reserva del equipo informatico ha expirado";
			}
		}
		this.mensaje=mensaje;
		return mensaje;
	}
	

}
