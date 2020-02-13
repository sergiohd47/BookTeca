package dad.web.bookteca.clases;

import java.sql.Date;
import javax.persistence.*;

@Entity
public class Revista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String editorial;
	private int fasciculo;
	private String genero;
	private boolean disponible;
	
	@ManyToOne
	private Usuario idUsuario;
	
	private Date fecInicio;
	private Date fecFin;
	
	protected Revista() {}
	
	public Revista(String nombre, String editorial, int fasciculo, String genero) {
		this.nombre = nombre;
		this.editorial = editorial;
		this.fasciculo = fasciculo;
		this.genero = genero;
		
		this.disponible = true;
		this.idUsuario = null;
		this.fecInicio = null;
		this.fecFin = null;
	}
	
	public long getId() {
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
	
	public String getGenero() {
		return genero;
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
		return "Revista [id=" + id + ", nombre=" + nombre + ", editorial=" + editorial + ", fasciculo=" + fasciculo
				+ ", generos=" + genero + "]";
	}
	
	
	
}
