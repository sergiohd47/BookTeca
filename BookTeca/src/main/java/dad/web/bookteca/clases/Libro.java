package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Libro {
	
	private int id;
	private String nombre;
	private String autor;
	private String editorial;
	private String genero;
	
	public Libro(int id, String nombre, String autor, String editorial, String genero) {
		this.id=id;
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;

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
	

	public String getEditorial() {
		return editorial;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", generos=" + genero + "]";
	}
	
	
}
