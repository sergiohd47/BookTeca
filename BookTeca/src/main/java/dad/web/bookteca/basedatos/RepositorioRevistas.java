package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.Revista;

public interface RepositorioRevistas extends JpaRepository<Revista, Integer>{
	
	ArrayList<Revista> findByNombre(String nombre);
	
	ArrayList<Revista> findByEditorial(String editorial);
	
	ArrayList<Revista> findById(int id);
	
	Revista findByIdUsuario(int idUsuario);
}
