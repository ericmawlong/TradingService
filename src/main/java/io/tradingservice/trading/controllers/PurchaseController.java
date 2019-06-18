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
import java.net.URISyntaxException;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("/trades")
public class PurchaseController {

    @Autowired
    private UserTradeService userTradeService;

    //create new user
    @POST
    @Consumes("application/json")
    @Path("/create/{userId}")
    public Response addTrade(@PathParam("userId") String userId, Trade trade) throws URISyntaxException{
        userTradeService.addTrade(userId, trade);
        return Response.status(201).entity("Requested trade completed").build();
    }

    //get all users
    @GET
    @Produces("application/json")
    @Path("/users")
    public List<User> getTradesByUser() throws URISyntaxException{
        return userTradeService.getAllUsers();
    }

    //get user by userId
    @GET
    @Produces("application/json")
    @Path("/get/{userId}")

    public List<Trade> getTradesByUser(@PathParam("userId") String userId) throws URISyntaxException{
        return userTradeService.getTradesByUserId(userId);
    }

    /* get funds by fundNumber
    @GET
    @Produces("application/json")
    @Path("/get/{userId}/{fundNumber}")
    public List<Trade> getTradesByFund(@PathParam("userId") String userId,
                                       @PathParam("fundNumber") String fundNumber) throws URISyntaxException{
        return userTradeService.getTradesByFundNumber(userId, fundNumber);
    } */

    //delete all trades of user
    @DELETE
    @Path("/delete/{userId}")
    public Response deleteUser(@PathParam("userId") String userId) throws URISyntaxException {
        if (userId != null) {
            userTradeService.deleteUser(userId);
            return Response.status(200).entity("Sold all trades").build();
        }
        return Response.status(404).build();
    }

}
