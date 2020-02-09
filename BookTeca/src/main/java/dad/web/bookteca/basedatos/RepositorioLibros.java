package dad.web.bookteca.basedatos;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Libro;

public interface RepositorioLibros extends JpaRepository<Libro, Integer> {

	@Query(value="select libro from Libro libro where libro.titulo=?1")
	ArrayList<Libro> buscarPorNombre(String titulo);
	@Query(value="select libro from Libro libro where libro.autor=?1")
	ArrayList<Libro> buscarPorAutor(String autor);
	@Query(value="select libro from Libro libro where libro.editorial=?1")
	ArrayList<Libro> buscarPorEditorial(String editorial);
	
	
	ArrayList<Libro> buscarPorId(int id);
	
	Libro buscarPorUsuario(int idUsuario);
	
}
