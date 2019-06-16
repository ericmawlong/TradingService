package io.tradingservice.trading.configuration;

import io.tradingservice.trading.controllers.PurchaseController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(PurchaseController.class);
    }
}
