package com.example.cryptocurrencyservice.service.management;

import com.example.cryptocurrencyservice.client.CryptocurrencyHttpClient;
import com.example.cryptocurrencyservice.client.dto.CryptocurrencyDto;
import com.example.cryptocurrencyservice.entity.CryptocurrencyDataEntity;
import com.example.cryptocurrencyservice.service.CryptocurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Component
public class CryptocurrencyDefaultManager implements CryptocurrencyManager {

    private final CryptocurrencyHttpClient cryptocurrencyHttpClient;

    private final CryptocurrencyService cryptocurrencyService;

    private final ModelMapper modelMapper;

    // Here I would like to use the broker's message or Redis for caching and message
    // Queues and Pub/Sub system, I don't have enough time. But this might be the most appropriate solution.

    @Scheduled(fixedDelayString = "${updateFrequency}")
    @Override
    public void fetchDataAndSaveToDatabase() {
        List<CryptocurrencyDto> cryptocurrencies = cryptocurrencyHttpClient.getCryptocurrencies();
        if (!CollectionUtils.isEmpty(cryptocurrencies)) {
            List<CryptocurrencyDataEntity.CryptocurrencyEntity> cryptocurrencyEntity = cryptocurrencies.stream()
                    .map(source -> modelMapper.map(source, CryptocurrencyDataEntity.CryptocurrencyEntity.class))
                    .collect(Collectors.toList());
            cryptocurrencyService.fetchAndSaveCryptocurrency(cryptocurrencyEntity);
        }
    }


}
