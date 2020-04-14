package dad.web.ServicioInterno.basedatos;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import dad.web.ServicioInterno.clases.Usuario;

@CacheConfig(cacheNames="usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@CacheEvict(allEntries=true)
	Usuario save(Usuario usuario);
	
	@Cacheable
	Page<Usuario> findByAdministrador(boolean administrador, Pageable Page);
	
	@Cacheable
	Usuario findByEmail(String email);
	
	@Cacheable
	Usuario findByEmailAndContrasenya(String email,String contrasenya);
	
	@Cacheable
	Page<Usuario> findByNombre(String nombre, Pageable Page);
	
	@Cacheable
	Page<Usuario> findByNombreAndApellidos(String nombre, String apellidos, Pageable Page);
}
