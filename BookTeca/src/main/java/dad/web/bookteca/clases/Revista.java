package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Revista {
	
	private int id;
	private String nombre;
	private String editorial;
	private int fasciculo;
	private String genero;
	
	public Revista(int id, String nombre, String editorial, int fasciculo, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.editorial = editorial;
		this.fasciculo = fasciculo;
		this.genero = genero;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public int getFasciculo() {
		return fasciculo;
	}
	
	public String getGenero() {
		return genero;
	}

	@Override
	public String toString() {
		return "Revista [id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", fasciculo=" + fasciculo
				+ ", generos=" + genero + "]";
	}
	
	
	
}
