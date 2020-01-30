package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Libro {
	private int id;
	private String nombre;
	private String autor;
	private String editorial;
	private ArrayList<String> generos=new ArrayList<>();
	
	public Libro(int id, String nombre, String autor, String editorial, ArrayList<String> generos) {
		this.id=id;
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;
		this.generos=generos;
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
	
	public ArrayList<String> getGeneros() {
		return generos;
	}
	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", generos=" + generos + "]";
	}
	public String getEditorial() {
		return editorial;
	}
}
