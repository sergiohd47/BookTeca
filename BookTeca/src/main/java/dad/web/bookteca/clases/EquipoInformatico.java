package dad.web.bookteca.clases;

import java.sql.Date;
import javax.persistence.*;

@Entity
public class EquipoInformatico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String sistemaOperativo;
	private String localizacion;
	private boolean disponible;
	
	@OneToOne(mappedBy="puestoInformatico")
	private Usuario idUsuario;
	
	private Date fechaReserva;
	
	protected EquipoInformatico() {}
	
	public EquipoInformatico(String sistemaOperativo, String localizacion, boolean disponible) {
		this.sistemaOperativo = sistemaOperativo;
		this.localizacion = localizacion;
		this.disponible=disponible;
	}
	
	public long getId() {
		return id;
	}

	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}
	
	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void reservar(Usuario idUsuario, Date reserva) {
		this.disponible = false;
		this.idUsuario = idUsuario;
		this.fechaReserva = reserva;
	}
	
	@Override
	public String toString() {
		return "EquipoInformatico [id=" + id + ", sistemaOperativo=" + sistemaOperativo + 
				", localizacion=" + localizacion + "]";
	}

}
