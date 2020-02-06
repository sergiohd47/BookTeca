package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.web.bookteca.clases.SalaTrabajoGrupo;;;

public interface RepositorioSalasDeTrabajo extends JpaRepository<SalaTrabajoGrupo, Integer>{

	ArrayList<SalaTrabajoGrupo> buscarDisponibles(boolean disponibles);
	
	ArrayList<SalaTrabajoGrupo> buscarCompartidas(boolean compartida);
	
	ArrayList<SalaTrabajoGrupo> buscarPorId(int id);
	
	SalaTrabajoGrupo buscarPorUsuario(int idUsuario);
}
