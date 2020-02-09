package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.SalaTrabajoGrupo;;;

public interface RepositorioSalasDeTrabajo extends JpaRepository<SalaTrabajoGrupo, Integer>{
	
	ArrayList<SalaTrabajoGrupo> findByDisponible(boolean disponible);
	
	ArrayList<SalaTrabajoGrupo> findByCompartida(boolean compartida);
	
	ArrayList<SalaTrabajoGrupo> findById(int id);
	
	SalaTrabajoGrupo findByIdUsuario(int idUsuario);
}
