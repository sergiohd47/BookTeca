package dad.web.bookteca.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario {
	
	private final int MAX = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String email;
	private String nombre;
	private String apellidos;
	private String contrasenya;
	
	@OneToMany(mappedBy="idUsuario")
	private List<Libro> librosReservados = new ArrayList<>(MAX);
	
	@OneToMany(mappedBy="idUsuario")
	private List<Revista> revistasReservadas = new ArrayList<>(MAX);
	
	@OneToOne(cascade = CascadeType.ALL)
	private EquipoInformatico puestoInformatico;
	
	@OneToOne(cascade=CascadeType.ALL)
	private SalaTrabajoGrupo salaTrabajo;
	
	private boolean administrador;
	
	protected Usuario() {}
	
	public Usuario(String nombre, String apellidos, String contrasenya, String email, boolean admin) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrasenya = contrasenya;
		this.email = email;
		this.administrador = admin;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SalaTrabajoGrupo getSalaTrabajo() {
		return salaTrabajo;
	}

	public void setSalaTrabajoGrupo(SalaTrabajoGrupo salaTrabajo) {
		this.salaTrabajo = salaTrabajo;
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
	
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
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
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contraseña=" + 
				contrasenya + ", librosReservados=" + librosReservados + ", revistasReservadas=" + 
				revistasReservadas + ", puestoInformatico=" + puestoInformatico + "]";
	}
	
	
}
