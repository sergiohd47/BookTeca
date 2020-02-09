package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Usuario;

public interface RepositorioUsuarios extends JpaRepository<Usuario, Integer>{
	@Query(value="select usuario from Usuario usuario where usuario.administrador=?1")
	ArrayList<Usuario> buscarAdministradoresOUsuarios(boolean esAdmin);
	@Query(value="select usuario from Usuario usuario where usuario.email=?1")
	Usuario buscarPorCorreo(String email);
	@Query(value="select usuario from Usuario usuario where usuario.nombre=?1")
	ArrayList<Usuario> buscarPorNombre(String nombre);
}
