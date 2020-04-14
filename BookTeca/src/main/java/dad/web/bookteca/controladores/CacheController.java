package dad.web.bookteca.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value="/cache/libros", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentLibro(){
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("libros");
		return cache.getNativeCache();
	}
	
	@RequestMapping(value="/cache/revistas", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentRevista(){
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("revistas");
		return cache.getNativeCache();
	}

	@RequestMapping(value="/cache/equipos", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentEquipoInformatico(){
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("equipos");
		return cache.getNativeCache();
	}
	
	@RequestMapping(value="/cache/salas", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentSalaTrabajoGrupo(){
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("salas");
		return cache.getNativeCache();
	}
	
	@RequestMapping(value="/cache/usuarios", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentUsuario(){
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("usuarios");
		return cache.getNativeCache();
	}

}
