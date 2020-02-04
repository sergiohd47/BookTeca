package dad.web.bookteca.clases;

import java.util.ArrayList;

public class Usuario {
	
	private final int MAX = 3;
	
	private int id;
	private String nombre;
	private String apellidos;
	private ArrayList<Libro> librosReservados;
	private ArrayList<Revista> revistasReservadas;
	private int puestoInformatico;
	private int salaTrabajoGrupo;
	private boolean administrador;
	
	public Usuario(int id, String nombre, String apellidos, boolean admin) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.administrador = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalaTrabajoGrupo() {
		return salaTrabajoGrupo;
	}

	public void setSalaTrabajoGrupo(int salaTrabajoGrupo) {
		this.salaTrabajoGrupo = salaTrabajoGrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
	
	public boolean getAdministrador() {
		return administrador;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public ArrayList<Libro> getLibrosReservados() {
		return librosReservados;
	}

	public void setLibrosReservados(ArrayList<Libro> librosReservados) {
		this.librosReservados = librosReservados;
	}
	
	public void addLibrosReservados(Libro libro) {
		this.librosReservados.add(libro);
	}
	
	public void removeLibrosReservados(Libro libro) {
		this.librosReservados.remove(libro);
	}

	public ArrayList<Revista> getRevistasReservadas() {
		return revistasReservadas;
	}

	public void setRevistasReservadas(ArrayList<Revista> revistasReservadas) {
		this.revistasReservadas = revistasReservadas;
	}
	
	public void addRevistasReservadas(Revista revista) {
		this.revistasReservadas.add(revista);
	}
	
	public void removeRevistasReservadas(Revista revista) {
		this.revistasReservadas.remove(revista);
	}

	public int getPuestoInformatico() {
		return puestoInformatico;
	}

	public void setPuestoInformatico(int puestoInformatico) {
		this.puestoInformatico = puestoInformatico;
	}
	
	public void reservarLibro(Libro l) {
		if(!librosReservados.contains(l))
			librosReservados.add(l);
	}
	
	public void reservarRevista(Revista r) {
		if(!revistasReservadas.contains(r))
			revistasReservadas.add(r);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + 
				", librosReservados=" + librosReservados + ", revistasReservadas=" + revistasReservadas + 
				", puestoInformatico=" + puestoInformatico + "]";
	}
	
	
}
