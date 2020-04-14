package dad.web.ServicioInterno.basedatos;

import java.util.ArrayList;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.ServicioInterno.clases.Revista;
import dad.web.ServicioInterno.clases.Usuario;

@CacheConfig(cacheNames="revistas")
public interface RevistaRepository extends JpaRepository<Revista, Long>{
	/*@Query(nativeQuery = true, value = "SELECT * FROM Revista GROUP BY nombre")
	ArrayList<Revista> findByNombre();*/
	
	@CacheEvict(allEntries=true)
	Revista save(Revista revista);
	
	@Cacheable
	ArrayList<Revista> findByNombre(String nombre);
	
	@Cacheable
	ArrayList<Revista> findByEditorial(String editorial);
	
	@Cacheable
	Revista findById(long id);
	
	@Cacheable
	ArrayList<Revista> findByNombreOrEditorialOrGenero(String nombre, String editorial, String genero);
	
	@Cacheable
	ArrayList<Revista> findByIdUsuario(Usuario idUsuario);
}