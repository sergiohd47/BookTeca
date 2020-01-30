package dad.web.bookteca.clases;

public class EquipoInformatico {
	private int id;
	private String sistemaOperativo;
	private String localizacion;
	
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


	@Override
	public String toString() {
		return "EquipoInformatico [id=" + id + ", sistemaOperativo=" + sistemaOperativo + ", localizacion="
				+ localizacion + "]";
	}
	
	
	

}
