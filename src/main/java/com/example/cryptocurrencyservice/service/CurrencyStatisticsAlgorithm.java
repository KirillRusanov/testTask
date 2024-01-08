package com.example.cryptocurrencyservice.service;

import com.example.cryptocurrencyservice.entity.CryptocurrencyDataEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyStatisticsAlgorithm {


    public Map<String, CurrencyStatistics> getCurrencyStatistics(List<CryptocurrencyDataEntity> currencyLists) {

        Map<String, CurrencyStatistics> currencyStatisticsMap = new HashMap<>();

        for (CryptocurrencyDataEntity cryptocurrencyDataEntity : currencyLists) {

            for (CryptocurrencyDataEntity.CryptocurrencyEntity currency : cryptocurrencyDataEntity.getCryptocurrencyList()) {
                String symbol = currency.getSymbol();
                double price = currency.getPrice();

                if (!currencyStatisticsMap.containsKey(symbol)) {
                    currencyStatisticsMap.put(symbol, new CurrencyStatistics(price, price));
                } else {
                    CurrencyStatistics statistics = currencyStatisticsMap.get(symbol);
                    statistics.setMinPrice(Math.min(statistics.getMinPrice(), price));
                    statistics.setMaxPrice(Math.max(statistics.getMaxPrice(), price));
                }
            }
        }
        return currencyStatisticsMap;
    }
}

@AllArgsConstructor
@Getter
@Setter
class CurrencyStatistics {

    private double minPrice;

    private double maxPrice;

}