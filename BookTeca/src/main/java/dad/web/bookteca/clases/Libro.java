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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", generos=" + generos + "]";
	}
	
	public String getEditorial() {
		return editorial;
	}
	
}
