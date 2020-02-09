package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Revista;

public interface RepositorioRevistas extends JpaRepository<Revista, Integer>{
	@Query(value="select revista from Revista revista where revista.nombre=?1")
	ArrayList<Revista> buscarPorNombre(String titulo);
	
	//ArrayList<Revista> buscarPorAutor(String autor); LAS REVISTAS NO TIENEN AUTOR
	@Query(value="select revista from Revista revista where revista.editorial=?1")
	ArrayList<Revista> buscarPorEditorial(String editorial);
	
	ArrayList<Revista> buscarPorId(int id);
	
	Revista buscarPorUsuario(int idUsuario);
}
