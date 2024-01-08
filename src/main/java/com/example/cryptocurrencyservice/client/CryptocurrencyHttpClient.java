package com.example.cryptocurrencyservice.client;

import com.example.cryptocurrencyservice.client.dto.CryptocurrencyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CryptocurrencyHttpClient {

    private final RestTemplate restTemplate;
    private final String url;

    public CryptocurrencyHttpClient(@Value("${cryptocurrency.service.url}") String url) {
        this.restTemplate = new RestTemplate();
        this.url = url;
    }

    public List<CryptocurrencyDto> getCryptocurrencies() {
        ResponseEntity<List<CryptocurrencyDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }
}