package io.tradingservice.trading.repositories;

import com.google.common.base.Optional;
import io.tradingservice.trading.models.*;
import org.immutables.mongo.repository.RepositorySetup;

import java.util.ArrayList;
import java.util.List;

public class UserAccessObject {
    UserRepository userRepository;
    UserRepository.Criteria where;

    public UserAccessObject(){
        userRepository = new UserRepository(RepositorySetup.forUri("mongodb://localhost:27017/Users"));
        where = userRepository.criteria();
    }

    public void addTrade(String userInput, Trade trade){
        userRepository.upsert(
                ImmutableUser.builder()
                        .userId(userInput)
                        .addTrades(trade)
                        .build()
        );

    }

    public List<ImmutableUser> getAllUsers(){
        List<User> users = userRepository.findAll().fetchAll().getUnchecked();
        List<ImmutableUser> u = new ArrayList<>();
        for (User user: users){
            u.add(ImmutableUser.builder().from(user).build());
        }
        return u;
    }

    public List<Trade> getTradesByUserId(String userInput){
        User user = userRepository.findByUserId(userInput).fetchFirst().getUnchecked().get();
        return user.trades();
    }


    public Optional<User> deleteUser(String userInput) {
        return userRepository.findByUserId(userInput).deleteFirst().getUnchecked();
    }

    public boolean modifyTrade(String userInput, String fundNumber, Trade trade){
        User user = userRepository.findByUserId(userInput).fetchFirst().getUnchecked().get();
        List<Trade> currTrades = user.trades();
        for (Trade t: currTrades){
            if ((trade.fundNumber()==t.fundNumber())&&(trade.status()=="purchase")){
                float newQuantity = trade.quantity() + t.quantity();
                Trade updated = ImmutableTrade.builder()
                        .fundNumber(t.fundNumber())
                        .fundName(t.fundName())
                        .avgNav(t.avgNav())
                        .quantity(newQuantity)
                        .status("purchase")
                        .invManager(t.invManager())
                        .setCycle(t.setCycle())
                        .sAndPRating(t.sAndPRating())
                        .moodysRating(t.moodysRating())
                        .build();
                userRepository.upsert(ImmutableUser.builder()
                        .userId(userInput)
                        .addTrades(updated)
                        .build());
                return true;
            }
            if ((trade.fundNumber()==t.fundNumber())&&(trade.status()=="sell")){
                if (t.quantity()<trade.quantity()) {
                    float newQuantity = t.quantity() - trade.quantity();
                } else return false;
            }

        }
        return false;
    }

}
