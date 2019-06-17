package io.tradingservice.trading.services;

import io.tradingservice.trading.models.Trade;
import io.tradingservice.trading.models.User;
import io.tradingservice.trading.repositories.UserTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTradeService {

    @Autowired
    private UserTradeRepository userTradeRepository;
    private Trade newtrade;

    public void addTrade(String userId, Trade trade){
        if (!userTradeRepository.existsById(userId)){
            List<Trade> trades = new ArrayList<>();
            trades.add(trade);
            User user = new User(userId, trades);
            userTradeRepository.save(user);
        }
        else if (userTradeRepository.existsById(userId)){
            User user = userTradeRepository.findByUserId(userId);
            List<Trade> trades = user.getTrades();
            Trade trade1 = new Trade();
            trade1.getFundNumber();
            if (trade1.getFundNumber()== trade.getFundNumber()){
                if (trade1.getStatus()== "purchase")
                    trade.setQuantity(trade.getQuantity()+trade1.getQuantity());

                else if (trade1.getStatus() == "sell") {
                    if(trade.getQuantity()>trade1.getQuantity())
                        trade.setQuantity(trade.getQuantity()-trade1.getQuantity());
                }

            }
            trades.add(trade);
            user.setTrades(trades);
            userTradeRepository.save(user);
        }
    }

    public List<User> getAllUsers(){
        return userTradeRepository.findAll();
    }

    public List<Trade> getTradesByUserId(String userId){
        User user = userTradeRepository.findByUserId(userId);
        return user.getTrades();
    }

    /* not working
    public List<Trade> getTradesByFundNumber(String userId, String fundNumber){
        User user = userTradeRepository.findByUserId(userId).findByFundNumber(fundNumber);
        return user.getTrades();
    }

    public void deleteFund (String userId, String fundNumber) {
        User user = userTradeRepository.findByUserId(userId);
        user.getFundNumber(fundNumber);
    } */

    public void deleteUser (String userId) {
        userTradeRepository.deleteById(userId);
    }

}
