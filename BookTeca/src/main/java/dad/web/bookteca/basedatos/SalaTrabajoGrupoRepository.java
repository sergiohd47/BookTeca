package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;;;

public interface SalaTrabajoGrupoRepository extends JpaRepository<SalaTrabajoGrupo, Long>{
	
	ArrayList<SalaTrabajoGrupo> findByDisponible(boolean disponible);
	
	ArrayList<SalaTrabajoGrupo> findByCompartida(boolean compartida);
	
	SalaTrabajoGrupo findById(long id);
	
	SalaTrabajoGrupo findByIdUsuario(Usuario idUsuario);
}
