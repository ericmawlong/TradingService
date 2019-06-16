package io.tradingservice.trading.controllers;

import io.tradingservice.trading.models.Trade;
import io.tradingservice.trading.models.User;
import io.tradingservice.trading.services.UserTradeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("/trades")
public class PurchaseController {

    @Autowired
    private UserTradeService userTradeService;

    @POST
    @Consumes("application/json")
    @Path("/create/{userId}")
    public Response addTrade(@PathParam("userId") String userId, Trade trade){
        userTradeService.addTrade(userId, trade);
        return Response.status(201).build();
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/users")
    public List<User> getTradesByUser(){
        return userTradeService.getAllUsers();
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/userId/{userId}")
    public List<Trade> getTradesByUser(@PathParam("userId") String userId){
        return userTradeService.getTradesByUserId(userId);
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/fundNumber/{fundNumber}")
    public List<Trade> getTradesByFund(@PathParam("fundNumber") String fundNumber){
        return userTradeService.getTradesByFundNumber(fundNumber);
    }

    @DELETE
    @Path("/delete/{userId}")
    public Response deleteUser(@PathParam("userId") String userId) {
        if (userId != null) {
            userTradeService.deleteUser(userId);
            return Response.status(200).build();
        }
        return Response.status(404).build();

    }

}
