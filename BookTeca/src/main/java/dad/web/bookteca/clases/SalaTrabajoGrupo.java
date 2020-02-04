package dad.web.bookteca.clases;

public class SalaTrabajoGrupo {
	
	private int id;
	private int capacidad;
	private String localizacion;
	private boolean compartida;
	
	private boolean disponible;
	private int idUsuario;
	
	public SalaTrabajoGrupo(int id, int capacidad, String localizacion, boolean compartida) {
		this.id = id;
		this.capacidad = capacidad;
		this.localizacion = localizacion;
		this.compartida = compartida;
		this.disponible = true;
	}
	
	public int getId() {
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

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void reservar(int idUsuario) {
		this.disponible = false;
		this.idUsuario = idUsuario;
	}
	
	@Override
	public String toString() {
		return "SalaTrabajoGrupo [id=" + id + ", capacidad=" + capacidad + ", localizacion=" + 
				localizacion + ", compartida=" + compartida + "]";
	}

}
