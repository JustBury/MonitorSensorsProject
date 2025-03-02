package by.bury.sensorstatisticsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SensorStatisticConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
