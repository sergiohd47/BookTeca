package dad.web.ServicioInterno.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value="/cache/libros", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentLibro() {
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache("libros");
		return cache.getNativeCache();
	}
	
	@RequestMapping(value="/cache/revistas", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentRevista(){
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache("revistas");
		return cache.getNativeCache();
	}

	@RequestMapping(value="/cache/equipos", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentEquipoInformatico(){
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache("equipos");
		return cache.getNativeCache();
	}
	
	@RequestMapping(value="/cache/salas", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentSalaTrabajoGrupo(){
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache("salas");
		return cache.getNativeCache();
	}
	
	@RequestMapping(value="/cache/usuarios", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentUsuario(){
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache("usuarios");
		return cache.getNativeCache();
	}

}
