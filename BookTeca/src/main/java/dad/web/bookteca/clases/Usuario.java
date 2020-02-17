package dad.web.bookteca.clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

import dad.web.bookteca.controladores.InicioController;

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
	public void setAdministrador(boolean tipo) {
		this.administrador=tipo;
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
	
	public boolean reservarLibro(Libro l) {
		if (l.isDisponible()){
			if (librosReservados.size()<MAX) {
				librosReservados.add(l);
				Calendar calendar = Calendar.getInstance();
				Date inicio = Date.valueOf(java.time.LocalDate.now());
				calendar.setTime(inicio); 
			    calendar.add(Calendar.DAY_OF_YEAR , 7);
				Date fin = (Date) calendar.getTime();
				l.reservar(this, inicio, fin);
				return true;
			}
		}
		return false;
	}
	
	public boolean quitarLibro(Libro l) {
		if (l.getIdUsuario()==this) {
			l.quitar();
			this.removeLibrosReservados(l);
			return true;
		}
		
		return false;
	}
	
	
	public boolean quitarRevista(Revista r) {
		if (r.getIdUsuario()==this) {
			r.quitar();
			this.removeRevistasReservadas(r);
			return true;
		}
		
		return false;
	}
	
	public boolean reservarRevista(Revista r) {
		if (r.isDisponible()){
			if (revistasReservadas.size()<3) {
				revistasReservadas.add(r);
				Calendar calendar = Calendar.getInstance();
			    
				Date inicio = Date.valueOf(java.time.LocalDate.now());
				calendar.setTime(inicio); 
			    calendar.add(Calendar.DAY_OF_YEAR , 7);
				Date fin = (Date) calendar.getTime();
				r.reservar(this, inicio, fin);
				return true;
			}
		}
		return false;
	}
	

	public boolean reservarPuestoInformatico(EquipoInformatico puestoInformatico) {
		if (puestoInformatico.isDisponible()){
			if (this.getPuestoInformatico()==null) {
				this.setPuestoInformatico(puestoInformatico);			    
				Date fecha = Date.valueOf(java.time.LocalDate.now());
				puestoInformatico.reservar(this, fecha);
				return true;
			}
		}
		return false;
	}
	
	public boolean quitarPuestoInformatico(EquipoInformatico puestoInformatico) {
		if (puestoInformatico.getIdUsuario()==this) {
			puestoInformatico.quitar();
			this.setPuestoInformatico(null);
			return true;
		}
		
		return false;
	}
	
	public boolean reservarSalaTrabajoGrupo(SalaTrabajoGrupo salaTrabajo) {
		if (salaTrabajo.isDisponible()){
			if (this.getPuestoInformatico()==null) {
				this.setSalaTrabajoGrupo(salaTrabajo);			    
				Date fecha = Date.valueOf(java.time.LocalDate.now());
				salaTrabajo.reservar(this, fecha);
				return true;
			}
		}
		return false;
	}
	
	public boolean quitarSalaTrabajoGrupo(SalaTrabajoGrupo salaTrabajo) {
		if (salaTrabajo.getIdUsuario()==this) {
			salaTrabajo.quitar();
			this.setPuestoInformatico(null);
			return true;
		}
		
		return false;
	}
	
	public boolean esContrasenya(String contrasenya) {
		return this.contrasenya == contrasenya;
	}
	
	public void cambiarRol() {
		this.administrador = !this.administrador;
	}
	
	public void darBaja() {
		if(this.librosReservados.size()>0) {
			for (Libro libro: this.librosReservados) {
				quitarLibro(libro);
			}
		}
		if(this.revistasReservadas.size()>0) {
			for (Revista revista: this.revistasReservadas) {
				this.quitarRevista(revista);
			}
		}
		if (this.puestoInformatico != null) {
			quitarPuestoInformatico(this.puestoInformatico);
		}
		if (this.salaTrabajo != null) {
			quitarSalaTrabajoGrupo(this.salaTrabajo);
		}
		if(this.administrador) {
			cambiarRol();
		}
	}
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contraseña=" + 
				contrasenya + ", librosReservados=" + librosReservados + ", revistasReservadas=" + 
				revistasReservadas + ", puestoInformatico=" + puestoInformatico + "]";
	}
	
	
}
