package dad.web.bookteca.clases;

public class SalaTrabajoGrupo {
	
	private int id;
	private int capacidad;
	private String localizacion;
	private boolean compartida;
	
	public SalaTrabajoGrupo(int id, int capacidad, String localizacion, boolean compartida) {
		this.id = id;
		this.capacidad = capacidad;
		this.localizacion = localizacion;
		this.compartida = compartida;
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
	
	@Override
	public String toString() {
		return "SalaTrabajoGrupo [id=" + id + ", capacidad=" + capacidad + ", localizacion=" + 
				localizacion + ", compartida=" + compartida + "]";
	}

}
