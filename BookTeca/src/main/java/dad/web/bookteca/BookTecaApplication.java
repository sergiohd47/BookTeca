package dad.web.bookteca;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class BookTecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTecaApplication.class, args);
	}
	
	@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager scmBookTeca = new SimpleCacheManager();
        scmBookTeca.setCaches(Arrays.asList(new ConcurrentMapCache("usuarios"),new ConcurrentMapCache("libros"),
        		new ConcurrentMapCache("revistas"),new ConcurrentMapCache("salas"),new ConcurrentMapCache("equipos")));
        return scmBookTeca;
        
	}
	
	
}
