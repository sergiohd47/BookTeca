package dad.web.bookteca;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@EnableCaching
@SpringBootApplication
public class BookTecaApplication {
	
	private static final Log LOG = LogFactory.getLog(BookTecaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookTecaApplication.class, args);
	}
	
	@Primary
	@Bean
	public CacheManager cacheManagerUsuario() {
		LOG.info("Activa cache...");			
		return new ConcurrentMapCacheManager("usuarios");
	}
	
	@Bean
	public CacheManager cacheManagerLibro() {
		LOG.info("Activa cache...");			
		return new ConcurrentMapCacheManager("libros");
	}
	
	@Bean
	public CacheManager cacheManagerRevista() {
		LOG.info("Activa cache...");			
		return new ConcurrentMapCacheManager("revistas");
	}
	
	@Bean
	public CacheManager cacheManagerSalaTrabajoGrupo() {
		LOG.info("Activa cache...");			
		return new ConcurrentMapCacheManager("salas");
	}
	
	@Bean
	public CacheManager cacheManagerEquipoInformatico() {
		LOG.info("Activa cache...");			
		return new ConcurrentMapCacheManager("equipos");
	}


}
