package dad.web.bookteca.clases;

import java.sql.Date;

public class EquipoInformatico {
	
	private int id;
	private String sistemaOperativo;
	private String localizacion;
	
	private boolean disponible;
	private int idUsuario;
	private Date fechaReserva;
	
	public EquipoInformatico(int id, String sistemaOperativo, String localizacion) {
		this.id = id;
		this.sistemaOperativo = sistemaOperativo;
		this.localizacion = localizacion;
	}
	
	public int getId() {
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

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void reservar(int idUsuario, Date reserva) {
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
