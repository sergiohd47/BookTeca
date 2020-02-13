package dad.web.bookteca.clases;

import java.sql.Date;
import javax.persistence.*;

@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String autor;
	private String editorial;
	private String genero;
	private boolean disponible;
	
	@ManyToOne
	private Usuario idUsuario;
	
	private Date fecInicio;
	private Date fecFin;
	
	//ConstructorBBDD
	protected Libro() {}
	
	public Libro(String nombre, String autor, String editorial, String genero ) {
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;
		this.genero=genero;
		
		this.disponible=true;
		this.idUsuario=null;
		this.fecInicio=null;
		this.fecFin=null;
		
	}
	
	public long getId() {
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
	
	public boolean isDisponible() {
		return disponible;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}
	
	public Date getFecInicio() {
		return fecInicio;
	}

	public Date getFecFin() {
		return fecFin;
	}

	public void reservar(Usuario idUsuario, Date inicio, Date fin) {
		this.disponible = false;
		this.idUsuario = idUsuario;
		this.fecInicio = inicio;
		this.fecFin = fin;
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", generos=" + genero + "]";
	}
	
	
	
}
