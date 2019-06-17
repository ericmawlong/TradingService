package io.tradingservice.trading.models;

//import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
//@Value.Immutable
public class User {

    @Id
    private String userId;

    private List<Trade> trades;


    public User(String userId, List<Trade> trades) {
        this.userId = userId;
        this.trades = trades;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }
}
