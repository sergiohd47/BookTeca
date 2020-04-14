package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.Usuario;

@CacheConfig(cacheNames="libros")
public interface LibroRepository extends JpaRepository<Libro, Long> {
	/*@Query(nativeQuery = true, value = "SELECT * FROM Libro GROUP BY nombre")
	ArrayList<Libro> findByNombre();*/
	
	@CacheEvict(allEntries=true)
	Libro save(Libro libros);

	@Cacheable
	ArrayList<Libro> findByNombre(String nombre);
	
	@Cacheable
	ArrayList<Libro> findByAutor(String autor);
	
	@Cacheable
	ArrayList<Libro> findByEditorial(String editorial);
	
	@Cacheable
	ArrayList<Libro> findByGenero(String genero);

	@Cacheable
	ArrayList<Libro> findByNombreOrAutorOrEditorialOrGenero(String nombre, String autor, String editorial, String genero);
	
	@Cacheable
	Libro findById(long id);
	
	@Cacheable
	ArrayList<Libro> findByIdUsuario(Usuario idUsuario);
}
