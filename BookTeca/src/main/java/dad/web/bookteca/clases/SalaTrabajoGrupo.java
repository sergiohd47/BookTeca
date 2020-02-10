package dad.web.bookteca.clases;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class SalaTrabajoGrupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int capacidad;
	private String localizacion;
	private boolean compartida;
	private boolean disponible;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario idUsuario;
	
	private Date fechaReserva;
	
	public SalaTrabajoGrupo(int capacidad, String localizacion, boolean compartida) {
		this.capacidad = capacidad;
		this.localizacion = localizacion;
		this.compartida = compartida;
		this.disponible = true;
	}
	
	public long getId() {
		return id;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public String getLocalizacion() {
		return localizacion;
	}
	
	public boolean isCompartida() {
		return compartida;
	}
	
	public boolean isDisponible() {
		return disponible;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}
	
	public void reservar(Usuario idUsuario) {
		this.disponible = false;
		this.idUsuario = idUsuario;
	}
	
	@Override
	public String toString() {
		return "SalaTrabajoGrupo [id=" + id + ", capacidad=" + capacidad + ", localizacion=" + 
				localizacion + ", compartida=" + compartida + "]";
	}

}
