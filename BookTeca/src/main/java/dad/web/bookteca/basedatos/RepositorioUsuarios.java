package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.web.bookteca.clases.Usuario;

public interface RepositorioUsuarios extends JpaRepository<Usuario, Integer>{

	ArrayList<Usuario> buscarAdministradoresOUsuarios(boolean esAdmin);
	
	Usuario buscarPorCorreo(String email);
	
	ArrayList<Usuario> buscarPorNombre(String nombre);
}
