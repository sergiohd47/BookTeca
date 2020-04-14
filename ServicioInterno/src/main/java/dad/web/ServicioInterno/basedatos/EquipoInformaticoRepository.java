package dad.web.ServicioInterno.basedatos;

import java.util.ArrayList;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.ServicioInterno.clases.EquipoInformatico;
import dad.web.ServicioInterno.clases.Usuario;



@CacheConfig(cacheNames="equipos")
public interface EquipoInformaticoRepository extends JpaRepository<EquipoInformatico, Long>{
	
	@CacheEvict(allEntries=true)
	EquipoInformatico save(EquipoInformatico equipo);
	
	@Cacheable
	ArrayList<EquipoInformatico> findByDisponible(boolean disponibles);
	
	@Cacheable
	ArrayList<EquipoInformatico> findBySistemaOperativo(String so);
	
	@Cacheable
	EquipoInformatico findByIdUsuario(Usuario idUsuario);
	
	@Cacheable
	EquipoInformatico findById(long id);
	
}


