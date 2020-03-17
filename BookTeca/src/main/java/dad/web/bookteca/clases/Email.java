package dad.web.bookteca.clases;

public class Email {
	private String nombreEmail;
	private long idRecurso;
	private String tipoAccion;
	public Email (String nombreEmail, long idRecurso, String tipoAccion) {
		this.nombreEmail=nombreEmail;
		this.idRecurso=idRecurso;
		this.tipoAccion=tipoAccion;
	}
	public String getNombreEmail() {
		return nombreEmail;
	}
	public long getIdRecurso() {
		return idRecurso;
	}
	public String getTipoAccion() {
		return tipoAccion;
	}
	@Override
	public String toString() {
		return "Email [nombreEmail=" + nombreEmail + ", idRecurso=" + idRecurso + ", tipoAccion=" + tipoAccion + "]";
	}
	
}
