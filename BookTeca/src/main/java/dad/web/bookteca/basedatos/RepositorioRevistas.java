package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.web.bookteca.clases.Revista;

public interface RepositorioRevistas extends JpaRepository<Revista, Integer>{

	ArrayList<Revista> buscarPorNombre(String titulo);
	
	ArrayList<Revista> buscarPorAutor(String autor);
	
	ArrayList<Revista> buscarPorEditorial(String editorial);
	
	ArrayList<Revista> buscarPorId(int id);
	
	Revista buscarPorUsuario(int idUsuario);
}
