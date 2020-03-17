package dad.web.ServicioInterno.clases;

import java.sql.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String nombre;
	@Column
	private String autor;
	@Column
	private String editorial;
	@Column
	private String genero;
	@Column
	private boolean disponible;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario idUsuario;
	
	@Column
	private Date fecInicio;
	@Column
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
	public void setIsDisponible(boolean bol) {
		this.disponible=bol;
	}

	public void reservar(Usuario idUsuario, Date inicio, Date fin) {
		this.disponible = false;
		this.idUsuario = idUsuario;
		this.fecInicio = inicio;
		this.fecFin = fin;
	}
	
	public void quitar() {
		this.disponible = true;
		this.idUsuario = null;
		this.fecInicio = null;
		this.fecFin = null;
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial
				+ ", generos=" + genero + "]";
	}
	
	
	
}
