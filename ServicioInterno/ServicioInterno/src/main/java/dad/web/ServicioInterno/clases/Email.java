package dad.web.ServicioInterno.clases;

import java.util.Date;

import dad.web.ServicioInterno.clases.Usuario;
import dad.web.ServicioInterno.clases.EquipoInformatico;
import dad.web.ServicioInterno.clases.Libro;
import dad.web.ServicioInterno.clases.Revista;
import dad.web.ServicioInterno.clases.SalaTrabajoGrupo;


public class Email {
	

	private Usuario idUsuario;
	private String mensaje;
	
	public Email(Usuario usuario) {
		this.idUsuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.idUsuario;
	}
	
	public void comprobarLibros() {
		String mensaje = "";
		Date hoy = new Date();
		for(Libro libro: this.idUsuario.getLibrosReservados()) {
			if (libro.getFecFin().getTime()<hoy.getTime()) {
				if (mensaje != "") {
					mensaje = "Libro sin devolver: " + libro.getNombre() + "("+ libro.getId() +")";
				}else {
					mensaje.concat(", " + libro.getNombre()+ "("+ libro.getId() +")");
				}
			}
		}
		this.mensaje=mensaje;

	}
	
	public void libroReservado(Libro libro) {
		this.mensaje = "Ha reservado el libro "+ libro.getNombre() +" con éxito";
	}
	
	public void comprobarRevistas() {
		String mensaje = "";
		Date hoy = new Date();
		for(Revista revista: this.idUsuario.getRevistasReservadas()) {
			if (revista.getFecFin().getTime()<hoy.getTime()) {
				if (mensaje != "") {
					mensaje = "Revista sin devolver: " + revista.getNombre() + "("+ revista.getId() +")";
				}else {
					mensaje.concat(", " + revista.getNombre()+ "("+ revista.getId() +")");
				}
			}
		}
		this.mensaje=mensaje;
		
	}
	
	public void revistaReservada(Revista revista) {
		this.mensaje = "Ha reservado la revista "+ revista.getNombre() +" con éxito";
	}
	
	
	public void comprobarSala() {
		String mensaje = "";
		Date hoy = new Date();
		if(idUsuario.getSalaTrabajo() != null) {
			if (idUsuario.getSalaTrabajo().getFechaReserva().getTime()<hoy.getTime()) {
				mensaje = "La reserva de la sala de trabajo ha expirado";
			}
		}
		this.mensaje=mensaje;

	}
	
	public void equipoReservado(EquipoInformatico equipo) {
		this.mensaje = "Ha reservado el equipo " + equipo.getLocalizacion() +" con éxito";
	}
	
	
	public void comprobarEquipo() {
		String mensaje = "";
		Date hoy = new Date();
		if(idUsuario.getPuestoInformatico() != null) {
			if (idUsuario.getPuestoInformatico().getFechaReserva().getTime()<hoy.getTime()) {
				mensaje = "La reserva del equipo informatico ha expirado";
			}
		}
		this.mensaje=mensaje;
		
	}
	
	public void salaReservada(SalaTrabajoGrupo sala) {
		this.mensaje = "Ha reservado la sala " + sala.getLocalizacion() +" con éxito";
	}
	
	public void mensajeError() {
		this.mensaje = "Lo sentimos " + idUsuario.getNombre() + " se ha producido un fallo al reservar este recurso";
	}
	
	public String getMensaje() {
		return this.mensaje;
	}

}
