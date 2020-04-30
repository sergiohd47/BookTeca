package dad.web.bookteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@EnableHazelcastHttpSession
@SpringBootApplication
public class BookTecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTecaApplication.class, args);
	}
	
	@Bean
	public Config config() {
		Config configuration=new Config();
		JoinConfig joinConfiguration=configuration.getNetworkConfig().getJoin();
		joinConfiguration.getMulticastConfig().setEnabled(false);
		List<String> listaIPDocker=new ArrayList<String>();
		listaIPDocker.add("172.19.0.7"); //contenedor_web 1
		listaIPDocker.add("172.19.0.5"); //contenedor_web 2
		joinConfiguration.getTcpIpConfig().setEnabled(true).setMembers(listaIPDocker);
		return configuration;
	}
	@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager scmBookTeca = new SimpleCacheManager();
        scmBookTeca.setCaches(Arrays.asList(new ConcurrentMapCache("usuarios"),new ConcurrentMapCache("libros"),
        		new ConcurrentMapCache("revistas"),new ConcurrentMapCache("salas"),new ConcurrentMapCache("equipos")));
        return scmBookTeca;
        
	}
	
	
}
