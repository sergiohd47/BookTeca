package dad.web.bookteca.basedatos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import dad.web.bookteca.clases.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Page<Usuario> findByAdministrador(boolean administrador, Pageable Page);
	
	Usuario findByEmail(String email);
	
	Usuario findByEmailAndContrasenya(String email,String contrasenya);
	
	Page<Usuario> findByNombre(String nombre, Pageable Page);
	
	Page<Usuario> findByNombreAndApellidos(String nombre, String apellidos, Pageable Page);
}
