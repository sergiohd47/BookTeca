package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Revista;

public interface RevistaRepository extends JpaRepository<Revista, Long>{
	@Query(nativeQuery = true, value = "SELECT (*) FROM Revista R GROUP BY R.Nombre")
	ArrayList<Revista> findAllGroupBy();
	
	ArrayList<Revista> findByNombre(String nombre);
	
	ArrayList<Revista> findByEditorial(String editorial);
	
	ArrayList<Revista> findById(long id);
	
	ArrayList<Revista> findByNombreOrEditorialOrGenero(String nombre, String editorial, String genero);
	
	Revista findByIdUsuario(long idUsuario);
}
