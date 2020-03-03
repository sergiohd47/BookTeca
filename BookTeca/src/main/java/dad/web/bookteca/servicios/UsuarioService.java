package dad.web.bookteca.servicios;

import org.springframework.stereotype.Component;

import dad.web.bookteca.clases.Usuario;

@Component
public class UsuarioService {
	
	private Usuario usuario;

	public Usuario getUsuarioLogueado() {
		return usuario;
	}

	public void setUsuarioLogueado(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean estaLogueado() {
		return (this.usuario != null);
	}
}
