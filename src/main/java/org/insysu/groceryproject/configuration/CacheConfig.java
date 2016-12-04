package org.insysu.groceryproject.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

/**
 * Created by buress on 12/4/16.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html#cache-store-configuration-jsr107
    //http://stackoverflow.com/questions/39386830/using-ehcache-3-with-spring-annotations-not-using-spring-boot

    @Bean
    public JCacheCacheManager cacheManager(){
        JCacheCacheManager cm = new JCacheCacheManager();
        cm.setCacheManager(jsr107cacheManager());
        return cm;
    }

    @Bean
    public CacheManager jsr107cacheManager(){
        //http://www.ehcache.org/documentation/3.1/107.html
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();

        MutableConfiguration<Long, String> configuration =
                new MutableConfiguration<Long, String>()
                        // Cannot set type for store! this may be a bug in spring or ehCache
                        //.setTypes(Long.class, String.class)
                        .setStoreByValue(false)
                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
        cacheManager.createCache("dishlist", configuration);
        cacheManager.createCache("deal", configuration);
        cacheManager.createCache("cuisinelist", configuration);
        cacheManager.createCache("user", configuration);
        cacheManager.createCache("row", configuration);
        cacheManager.createCache("cuisine", configuration);
        cacheManager.createCache("deallist", configuration);

        return cacheManager;
    }
}
