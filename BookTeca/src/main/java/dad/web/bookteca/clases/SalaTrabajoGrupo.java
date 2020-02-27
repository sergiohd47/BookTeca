package dad.web.bookteca.clases;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class SalaTrabajoGrupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private int capacidad;
	@Column
	private String localizacion;
	@Column
	private boolean compartida;
	@Column
	private boolean disponible;
	
	@JsonIgnore
	@OneToOne(mappedBy="salaTrabajo")
	private Usuario idUsuario;
	
	@Column
	private Date fechaReserva;
	
	protected SalaTrabajoGrupo() {}
	
	public SalaTrabajoGrupo(int capacidad, String localizacion, boolean compartida) {
		this.capacidad = capacidad;
		this.localizacion = localizacion;
		this.compartida = compartida;
		
		this.disponible = true;
		this.idUsuario = null;
		this.fechaReserva = null;
		
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
	
	public Date getFechaReserva() {
		return fechaReserva;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}
	
	public void reservar(Usuario idUsuario, Date fecha) {
		this.disponible = false;
		this.idUsuario = idUsuario;
		this.fechaReserva = fecha;
	}
	
	public void quitar() {
		this.disponible = true;
		this.idUsuario = null;
		this.fechaReserva = null;
	}
	
	
	@Override
	public String toString() {
		return "SalaTrabajoGrupo [id=" + id + ", capacidad=" + capacidad + ", localizacion=" + 
				localizacion + ", compartida=" + compartida + "]";
	}

}
