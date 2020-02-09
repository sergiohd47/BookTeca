package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.SalaTrabajoGrupo;;;

public interface RepositorioSalasDeTrabajo extends JpaRepository<SalaTrabajoGrupo, Integer>{
	@Query(value="select salaTrabajoGrupo from SalaTrabajoGrupo salaTrabajoGrupo where salaTrabajoGrupo.disponible=?1")
	ArrayList<SalaTrabajoGrupo> buscarDisponibles(boolean disponibles);
	@Query(value="select salaTrabajoGrupo from SalaTrabajoGrupo salaTrabajoGrupo where salaTrabajoGrupo.compartida=?1")
	ArrayList<SalaTrabajoGrupo> buscarCompartidas(boolean compartida);
	
	ArrayList<SalaTrabajoGrupo> buscarPorId(int id);
	
	SalaTrabajoGrupo buscarPorUsuario(int idUsuario);
}
