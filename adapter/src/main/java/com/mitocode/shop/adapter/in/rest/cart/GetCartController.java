package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.application.port.in.cart.GetCartUseCase;
import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.customer.CustomerId;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import static com.mitocode.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class GetCartController {

    private final GetCartUseCase getCartUseCase;

    @GET
    @Path("/{customerId}")
    public CartWebModel getCart(@PathParam("customerId") String customerIdString){
        CustomerId customerId = parseCustomerId(customerIdString);
        Cart cart = getCartUseCase.getCart(customerId);
        return  CartWebModel.fromDomainModel(cart);
    }

}
