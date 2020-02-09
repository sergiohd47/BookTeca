package dad.web.bookteca.basedatos;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Libro;

public interface RepositorioLibros extends JpaRepository<Libro, Integer> {

	ArrayList<Libro> findByNombre(String nombre);
	
	ArrayList<Libro> findByAutor(String autor);
	
	ArrayList<Libro> findByEditorial(String editorial);
	
	
	ArrayList<Libro> findById(int id);
	
	Libro findByIdUsuario(int idUsuario);
	
	
}
