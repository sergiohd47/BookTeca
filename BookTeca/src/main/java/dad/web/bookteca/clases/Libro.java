package dad.web.bookteca.clases;

import java.sql.Date;
import java.util.ArrayList;

public class Libro {
	
	private int id;
	private String nombre;
	private String autor;
	private String editorial;
	private String genero;
	
	private boolean disponible;
	private int idUsuario;
	private Date fecInicio;
	private Date fecFin;
	
	
	
	public Libro(int id, String nombre, String autor, String editorial, String genero ) {
		this.id=id;
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;
		this.genero=genero;
		this.disponible=true;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getAutor() {
		return autor;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public boolean isDisponible() {
		return disponible;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public Date getFecInicio() {
		return fecInicio;
	}

	public Date getFecFin() {
		return fecFin;
	}

	public void reservar(int idUsuario, Date inicio, Date fin) {
		this.disponible = false;
		this.idUsuario = idUsuario;
		this.fecInicio = inicio;
		this.fecFin = fin;
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", generos=" + genero + "]";
	}
	
	
}
