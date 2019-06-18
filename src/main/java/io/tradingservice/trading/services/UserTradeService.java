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

    //add new trades
    public void addTrade(String userId, Trade trade){
        //new userId
        if (!userTradeRepository.existsById(userId)){
            List<Trade> trades = new ArrayList<>();
            trades.add(trade);
            User user = new User(userId, trades);
            if(trade.getStatus().equals("purchase"))
                userTradeRepository.save(user);

        }
        //existing userId
        else if (userTradeRepository.existsById(userId)){
            User user = userTradeRepository.findByUserId(userId);
            List<Trade> trades = user.getTrades();
            int count = 0;      //count to check trade1 with all trade(s)
            for(Trade trade1: trades) {
                //existing fundNumber
                if (trade.getFundNumber().equals(trade1.getFundNumber())) {
                    if (trade.getStatus().equals("purchase")) {
                        trade.setAvgNav(((trade.getAvgNav() * trade.getQuantity()) + (trade1.getAvgNav() * trade1.getQuantity())) /
                                (trade.getQuantity() + trade1.getQuantity()));
                        trade.setQuantity(trade.getQuantity() + trade1.getQuantity());
                    }
                    if (trade.getStatus().equals("sell")) {
                        if (trade.getQuantity() <= trade1.getQuantity()) {
                            trade.setAvgNav(((trade1.getAvgNav() * trade1.getQuantity()) - (trade.getAvgNav() * trade.getQuantity())) /
                                    (trade.getQuantity() + trade1.getQuantity()));
                            trade.setQuantity(trade1.getQuantity() - trade.getQuantity());
                        }
                        else
                            break;      //quantity sold > quantity available
                    }
                    trades.remove(trade1);
                    trades.add(trade);
                    user.setTrades(trades);
                    userTradeRepository.save(user);
                    break;
                }
                count++;        //increments only if fundNumber not available in current trade
            }

            if (count==trades.size()){      //check if new fundNumber
                trades.add(trade);
                user.setTrades(trades);
                userTradeRepository.save(user);
            }
        }
    }

    //get all user info
    public List<User> getAllUsers(){
        return userTradeRepository.findAll();
    }

    //get user info according to userId
    public List<Trade> getTradesByUserId(String userId){
        User user = userTradeRepository.findByUserId(userId);
        return user.getTrades();
    }

     /* get fund info according to fundNumber
    public List<Trade> getTradesByFundNumber(String userId, String fundNumber){
        User user = userTradeRepository.findByUserId(userId);
        List<Trade> trades= user.getTrades();
        Trade trade = new Trade();
        if(trade.getFundNumber().equals(fundNumber));
            return user.getTrades();
    } */

    //delete all info of particular userId
    public void deleteUser (String userId) {
        userTradeRepository.deleteById(userId);
    }
}
