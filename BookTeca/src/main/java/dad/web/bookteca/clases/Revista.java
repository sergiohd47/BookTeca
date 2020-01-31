package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Revista {
	
	private int id;
	private String nombre;
	private String editorial;
	private int fasciculo;
	private int numeroEjemplares;
	private ArrayList<String> generos=new ArrayList<>();
	
	public Revista(int id, String nombre, String editorial, int fasciculo, int numeroEjemplares, ArrayList<String> generos) {
		this.id = id;
		this.nombre = nombre;
		this.editorial = editorial;
		this.fasciculo = fasciculo;
		this.numeroEjemplares=numeroEjemplares;
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

	public int getNumeroEjemplares() {
		return numeroEjemplares;
	}

	public void setNumeroEjemplares(int numeroEjemplares) {
		this.numeroEjemplares = numeroEjemplares;
	}

	@Override
	public String toString() {
		return "Revista [id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", fasciculo=" + fasciculo
				+ ", numeroEjemplares=" + numeroEjemplares + ", generos=" + generos + "]";
	}
	
	
	
}
