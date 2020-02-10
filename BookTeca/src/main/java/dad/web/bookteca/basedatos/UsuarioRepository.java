package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	ArrayList<Usuario> findByEsAdmin(boolean esAdmin);
	
	Usuario findByEmail(String email);
	
	ArrayList<Usuario> findByNombre(String nombre);
}
