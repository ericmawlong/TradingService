package io.tradingservice.trading.services;

import io.tradingservice.trading.models.Trade;
import io.tradingservice.trading.models.User;
import io.tradingservice.trading.repositories.UserTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTradeService {

    @Autowired
    private UserTradeRepository userTradeRepository;

    public void addTrade(String userInput, Trade trade){
        if (!userTradeRepository.existsById(userInput)){
            List<Trade> trades = new ArrayList<>();
            trades.add(trade);
            User user = new User(userInput, trades);
            userTradeRepository.save(user);
        }
        else if (userTradeRepository.existsById(userInput)){
            User user = userTradeRepository.findByUserId(userInput);
            List<Trade> trades = user.getTrades();
            trades.add(trade);
            user.setTrades(trades);
            userTradeRepository.save(user);
        }
    }


    public List<Trade> getTradesByUserId(String userId){
        User user = userTradeRepository.findByUserId(userId);
        return user.getTrades();
    }

    public List<User> getAllUsers(){
        return userTradeRepository.findAll();
    }
}
