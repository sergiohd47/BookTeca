package dad.web.bookteca.basedatos;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.web.bookteca.clases.Libro;

public interface RepositorioLibros extends JpaRepository<Libro, Integer> {

	ArrayList<Libro> buscarPorNombre(String titulo);
	
	ArrayList<Libro> buscarPorAutor(String autor);
	
	ArrayList<Libro> buscarPorEditorial(String editorial);
	
	ArrayList<Libro> buscarPorId(int id);
	
	Libro buscarPorUsuario(int idUsuario);
	
}
