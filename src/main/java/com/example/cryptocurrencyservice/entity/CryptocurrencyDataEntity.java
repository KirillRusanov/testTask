package com.example.cryptocurrencyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "cryptocurrencies")
public class CryptocurrencyDataEntity {

    @Id
    private String id;

    private List<CryptocurrencyEntity> cryptocurrencyList;

    private LocalDateTime updateTime;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class CryptocurrencyEntity {

        private String symbol;

        private Double price;

    }
}