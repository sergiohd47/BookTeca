package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.Usuario;

public interface RevistaRepository extends JpaRepository<Revista, Long>{
	/*@Query(nativeQuery = true, value = "SELECT * FROM Revista GROUP BY nombre")
	ArrayList<Revista> findByNombre();*/
	
	ArrayList<Revista> findByNombre(String nombre);
	
	ArrayList<Revista> findByEditorial(String editorial);
	
	Revista findById(long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM Revista WHERE nombre=?1 OR editorial=?2 OR genero=?3")
	ArrayList<Revista> findByNombreOrEditorialOrGenero(String nombre, String editorial, String genero);
	
	ArrayList<Revista> findByIdUsuario(Usuario idUsuario);
}
