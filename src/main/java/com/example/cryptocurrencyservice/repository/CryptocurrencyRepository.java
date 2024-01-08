package com.example.cryptocurrencyservice.repository;

import com.example.cryptocurrencyservice.entity.CryptocurrencyDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CryptocurrencyRepository extends MongoRepository<CryptocurrencyDataEntity, String> {

    List<CryptocurrencyDataEntity> findAllByTimestampAfter(LocalDateTime time);

}