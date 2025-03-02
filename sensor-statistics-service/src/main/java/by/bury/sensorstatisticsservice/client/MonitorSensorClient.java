package by.bury.sensorstatisticsservice.client;

import by.bury.sensorstatisticsservice.dto.SensorResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class MonitorSensorClient {


    private final RestTemplate restTemplate;

    public MonitorSensorClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String URL_TO_MONITOR_SERVICE = "http://localhost:8080/api/v1/sensor";
    private final String AUTH_URL = "http://localhost:8080/api/v1/auth/login";

    public List<SensorResponseDto> getSensorsInfo() {
        try {
            HttpEntity<String> httpEntity = getAuthHttpEntity();
            ResponseEntity<List<SensorResponseDto>> responseEntity = restTemplate.exchange(URL_TO_MONITOR_SERVICE,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<List<SensorResponseDto>>() {
                    });
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Can't get sensors info", e);
        }
    }


    private HttpEntity<String> getAuthHttpEntity() {

        ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(
                AUTH_URL,
                HttpMethod.POST,
                createLoginRequestHttpEntity(),
                new ParameterizedTypeReference<>() {
                }
        );

        Map<String, String> responseMap = responseEntity.getBody();
        String token = "Bearer " + responseMap.get("token");

        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, token);
        return new HttpEntity<>(headers);
    }

    public static HttpEntity<Map<String, String>> createLoginRequestHttpEntity() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "admin@gmail.com");
        requestBody.put("password", "1111");
        return new HttpEntity<>(requestBody);
    }

}
