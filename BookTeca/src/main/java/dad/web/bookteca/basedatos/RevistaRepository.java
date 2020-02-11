package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Revista;

public interface RevistaRepository extends JpaRepository<Revista, Long>{
	
	ArrayList<Revista> findByNombre(String nombre);
	
	ArrayList<Revista> findByEditorial(String editorial);
	
	ArrayList<Revista> findById(long id);
	
	ArrayList<Revista> findByRevistaNombreOrRevistaEditorialOrRevistaGenero(String nombre, String editorial, String genero);
	
	Revista findByIdUsuario(long idUsuario);
}
