package dad.web.ServicioInterno.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.ServicioInterno.clases.Libro;
import dad.web.ServicioInterno.clases.Revista;
import dad.web.ServicioInterno.clases.Usuario;

public interface LibroRepository extends JpaRepository<Libro, Long> {
	/*@Query(nativeQuery = true, value = "SELECT * FROM Libro GROUP BY nombre")
	ArrayList<Libro> findByNombre();*/

	ArrayList<Libro> findByNombre(String nombre);
	
	ArrayList<Libro> findByAutor(String autor);
	
	ArrayList<Libro> findByEditorial(String editorial);
	
	ArrayList<Libro> findByGenero(String genero);

	ArrayList<Libro> findByNombreOrAutorOrEditorialOrGenero(String nombre, String autor, String editorial, String genero);
	
	Libro findById(long id);
	
	ArrayList<Libro> findByIdUsuario(Usuario idUsuario);
	
	
}
