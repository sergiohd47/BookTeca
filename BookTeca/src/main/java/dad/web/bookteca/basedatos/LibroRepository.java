package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

	ArrayList<Libro> findByNombre(String nombre);
	
	ArrayList<Libro> findByAutor(String autor);
	
	ArrayList<Libro> findByEditorial(String editorial);
	
	ArrayList<Libro> findByNombreOrAutorOrEditorialOrGenero(String nombre, String autor, String editorial, String genero);
	
	ArrayList<Libro> findById(long id);
	
	Libro findByIdUsuario(long idUsuario);
	
	
}
