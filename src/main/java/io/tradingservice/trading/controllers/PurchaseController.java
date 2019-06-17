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

    @POST
    @Consumes("application/json")
    @Path("/create/{userId}")
    public Response addTrade(@PathParam("userId") String userId, Trade trade) throws URISyntaxException{
        userTradeService.addTrade(userId, trade);
        return Response.status(201).entity("Purchased requested trade")build();
    }

    @GET
    @Produces("application/json")
    @Path("/users")
    public List<User> getTradesByUser() throws URISyntaxException{
        return userTradeService.getAllUsers();
    }

    @GET
    @Produces("application/json")
    @Path("/userId/{userId}")
    public List<Trade> getTradesByUser(@PathParam("userId") String userId) throws URISyntaxException{
        return userTradeService.getTradesByUserId(userId);
    }

    @GET
    @Produces("application/json")
    @Path("/fundNumber/{fundNumber}")
    public List<Trade> getTradesByFund(@PathParam("fundNumber") String fundNumber) throws URISyntaxException{
        return userTradeService.getTradesByFundNumber(fundNumber);
    }

    @DELETE
    @Path("/delete/{userId}")
    public Response deleteUser(@PathParam("userId") String userId) throws URISyntaxException {
        if (userId != null) {
            userTradeService.deleteUser(userId);
            return Response.status(200).entity("Sold requested trade").build();
        }
        return Response.status(404).build();

    }

}
