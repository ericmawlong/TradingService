package io.tradingservice.trading.services;

import com.google.common.base.Optional;
import io.tradingservice.trading.models.ImmutableUser;
import io.tradingservice.trading.models.Trade;
import io.tradingservice.trading.models.User;
import io.tradingservice.trading.repositories.UserAccessObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTradeService {

    private UserAccessObject userRepository = new UserAccessObject();

    public void addTrade(String userInput, Trade trade){
        userRepository.addTrade(userInput, trade);
    }

    public List<ImmutableUser> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public List<Trade> getTradesByUserId(String userInput){
        return userRepository.getTradesByUserId(userInput);
    }


    public boolean deleteUser(String userInput) {
        Optional<User> user = userRepository.deleteUser(userInput);
        if (user.isPresent()){
            return false;
        } else return true;
    }

    public boolean updateTrade(String userInput, Trade trade){
        String fundNumber = trade.fundNumber();
        return userRepository.modifyTrade(userInput, fundNumber, trade);
    }


}