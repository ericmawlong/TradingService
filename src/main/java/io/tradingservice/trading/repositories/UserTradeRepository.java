package io.tradingservice.trading.repositories;

import io.tradingservice.trading.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTradeRepository extends MongoRepository<User, String> {
    public User findByUserId(String userId);
}
