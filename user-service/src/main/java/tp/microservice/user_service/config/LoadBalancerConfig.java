package tp.microservice.user_service.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class LoadBalancerConfig {

    @Bean
    @LoadBalanced // Active l'équilibrage de charge
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
