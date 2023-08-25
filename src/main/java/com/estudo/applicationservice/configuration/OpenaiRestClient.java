package com.estudo.applicationservice.configuration;

import com.estudo.applicationservice.rest.vo.OpenaiRequest;
import com.estudo.applicationservice.rest.vo.OpenaiResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenaiRestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public OpenaiResponse chat(final String baseUrl,
                               final String apiKey,
                               final OpenaiRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        System.out.println(headers);

        final HttpEntity<OpenaiRequest> entity = new HttpEntity<>(request, headers);

        final ResponseEntity<OpenaiResponse> responseEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                entity,
                OpenaiResponse.class
        );

        return responseEntity.getBody();
    }
}