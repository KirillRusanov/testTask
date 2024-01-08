package com.example.cryptocurrencyservice.service;

import com.example.cryptocurrencyservice.entity.CryptocurrencyDataEntity;
import com.example.cryptocurrencyservice.repository.CryptocurrencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CryptocurrencyService {

    private final CryptocurrencyRepository cryptocurrencyRepository;

    private final UserService userService;

    private final CryptocurrencyChangeTracker cryptocurrencyChangeTracker;

    public void fetchAndSaveCryptocurrency(List<CryptocurrencyDataEntity.CryptocurrencyEntity> cryptocurrencyEntities) {
        CryptocurrencyDataEntity cryptocurrencyData = CryptocurrencyDataEntity.builder()
                .cryptocurrencyList(cryptocurrencyEntities)
                .updateTime(LocalDateTime.now())
                .build();
        cryptocurrencyRepository.insert(cryptocurrencyData);
        notifyUsersAboutChanges(cryptocurrencyData);
    }

    public void notifyUsersAboutChanges(CryptocurrencyDataEntity cryptocurrencyData) {
        userService.getAllUser()
                .forEach(user -> cryptocurrencyChangeTracker.trackUser(user, cryptocurrencyData));
    }

}
