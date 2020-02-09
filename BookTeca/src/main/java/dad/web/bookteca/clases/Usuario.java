package dad.web.bookteca.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario {
	
	private final int MAX = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String email;;
	private String nombre;
	private String apellidos;
	@OneToMany(mappedBy="idUsuario")
	private List<Libro> librosReservados = new ArrayList<>(MAX);
	@OneToMany(mappedBy="idUsuario")
	private List<Revista> revistasReservadas = new ArrayList<>(MAX);
	@OneToOne(mappedBy="idUsuario")
	private EquipoInformatico puestoInformatico;
	@OneToOne(mappedBy="idUsuario")
	private SalaTrabajoGrupo salaTrabajoGrupo;
	private boolean administrador;
	
	public Usuario(String email, String nombre, String apellidos, boolean admin) {
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.administrador = admin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SalaTrabajoGrupo getSalaTrabajoGrupo() {
		return salaTrabajoGrupo;
	}

	public void setSalaTrabajoGrupo(SalaTrabajoGrupo salaTrabajoGrupo) {
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

	public List<Libro> getLibrosReservados() {
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

	public List<Revista> getRevistasReservadas() {
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

	public EquipoInformatico getPuestoInformatico() {
		return puestoInformatico;
	}

	public void setPuestoInformatico(EquipoInformatico puestoInformatico) {
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
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellidos=" + apellidos + 
				", librosReservados=" + librosReservados + ", revistasReservadas=" + revistasReservadas + 
				", puestoInformatico=" + puestoInformatico + "]";
	}
	
	
}
