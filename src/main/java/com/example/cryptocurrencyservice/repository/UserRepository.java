package com.example.cryptocurrencyservice.repository;

import com.example.cryptocurrencyservice.entity.CryptocurrencyDataEntity;
import com.example.cryptocurrencyservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}