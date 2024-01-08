package com.example.cryptocurrencyservice.service;

import com.example.cryptocurrencyservice.entity.CryptocurrencyDataEntity;
import com.example.cryptocurrencyservice.entity.User;
import com.example.cryptocurrencyservice.repository.CryptocurrencyRepository;
import com.example.cryptocurrencyservice.service.notification.CryptocurrencyNotifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class CryptocurrencyChangeTracker {

    private CryptocurrencyRepository cryptocurrencyRepository;

    private CurrencyStatisticsAlgorithm currencyStatisticsAlgorithm;

    private CryptocurrencyNotifier cryptocurrencyNotifier;

    void trackUser(User user, CryptocurrencyDataEntity currentCryptocurrencyData) {
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(user.getNotificationStartTime(), 0));
        List<CryptocurrencyDataEntity> allByTimestampAfter = cryptocurrencyRepository.findAllByTimestampAfter(startDateTime);

        Map<String, CurrencyStatistics> currencyStatistics = currencyStatisticsAlgorithm.getCurrencyStatistics(allByTimestampAfter);

        currentCryptocurrencyData.getCryptocurrencyList()
                .forEach(currentCurrency -> {
                            CurrencyStatistics currencyStatisticsForSelectedCurrency = currencyStatistics.get(currentCurrency.getSymbol());
                            double priceChangeInPercentageMin = ((currentCurrency.getPrice() - currencyStatisticsForSelectedCurrency.getMinPrice()) / currencyStatisticsForSelectedCurrency.getMinPrice()) * 100;
                            double priceChangeInPercentageMax = ((currentCurrency.getPrice() - currencyStatisticsForSelectedCurrency.getMaxPrice()) / currencyStatisticsForSelectedCurrency.getMaxPrice()) * 100;
                            if (priceChangeInPercentageMin > user.getSensitivePercentChanges()) {
                                cryptocurrencyNotifier.notifyUser("Changed min : " + priceChangeInPercentageMin);
                            }
                            if (priceChangeInPercentageMax > user.getSensitivePercentChanges()) {
                                cryptocurrencyNotifier.notifyUser("Changed man : " + priceChangeInPercentageMin);
                            }

                        }
                );
    }

}
