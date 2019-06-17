package io.tradingservice.trading.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.gson.Gson;
import org.immutables.mongo.Mongo;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTrade.class)
@JsonDeserialize(as = ImmutableTrade.class)
public abstract class Trade {

    // @Id
    // public Timestamp timeStamp;

    public abstract String fundNumber();

    public abstract String fundName();
    public abstract float avgNav();
    public abstract float quantity();
    public abstract String status();
    public abstract String invManager();
    // public abstract String invCurr();
    public abstract int setCycle();
    public abstract float sAndPRating();
    public abstract float moodysRating();


}
