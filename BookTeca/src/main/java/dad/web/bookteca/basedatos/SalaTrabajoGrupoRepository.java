package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;;;

@CacheConfig(cacheNames="salas")
public interface SalaTrabajoGrupoRepository extends JpaRepository<SalaTrabajoGrupo, Long>{
	
	@CacheEvict(allEntries=true)
	SalaTrabajoGrupo save(SalaTrabajoGrupo sala);
	
	@Cacheable
	ArrayList<SalaTrabajoGrupo> findByDisponible(boolean disponible);
	
	@Cacheable
	ArrayList<SalaTrabajoGrupo> findByCompartida(boolean compartida);
	
	@Cacheable
	SalaTrabajoGrupo findById(long id);
	
	@Cacheable
	SalaTrabajoGrupo findByIdUsuario(Usuario idUsuario);
}
