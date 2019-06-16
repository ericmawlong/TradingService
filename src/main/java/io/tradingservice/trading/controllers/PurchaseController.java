package io.tradingservice.trading.controllers;

import io.tradingservice.trading.models.Trade;
import io.tradingservice.trading.models.User;
import io.tradingservice.trading.services.UserTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
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
    @Path("/present/{userId}")
    public List<Trade> getTradesByUser(@PathParam("userId") String userId){
        return userTradeService.getTradesByUserId(userId);
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/users")
    public List<User> getTradesByUser(){
        return userTradeService.getAllUsers();
    }



}
