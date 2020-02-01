package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Libro {
	
	private int id;
	private String nombre;
	private String autor;
	private String editorial;
	private int numeroEjemplares;
	private String genero;
	
	public Libro(int id, String nombre, String autor, String editorial, int numeroEjemplares, String genero) {
		this.id=id;
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;
		this.numeroEjemplares=numeroEjemplares;
		this.genero=genero;
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
	

	
	public int getNumeroEjemplares() {
		return numeroEjemplares;
	}

	public void setNumeroEjemplares(int numeroEjemplares) {
		this.numeroEjemplares = numeroEjemplares;
	}

	public String getEditorial() {
		return editorial;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", numeroEjemplares=" + numeroEjemplares + ", generos=" + genero + "]";
	}
	
	
}
