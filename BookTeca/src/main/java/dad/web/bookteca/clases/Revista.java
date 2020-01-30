package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Revista {
	
	private int id;
	private String nombre;
	private String editorial;
	private int fasciculo;
	private ArrayList<String> generos=new ArrayList<>();
	
	public Revista(int id, String nombre, String editorial, int fasciculo, ArrayList<String> generos) {
		this.id = id;
		this.nombre = nombre;
		this.editorial = editorial;
		this.fasciculo = fasciculo;
		this.generos = generos;
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
	
	public ArrayList<String> getGeneros() {
		return generos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Revista other = (Revista) obj;
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
		return "Revista [id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", fasciculo=" + 
				fasciculo + ", generos=" + generos + "]";
	}	
	
}
