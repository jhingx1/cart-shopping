package com.mitocode.shop.adapter.in.rest.cart;
import com.mitocode.shop.application.port.in.cart.AddToCartUseCase;
import com.mitocode.shop.application.port.in.cart.ProductNotFoundException;
import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.cart.NotEnoughItemsInStockException;
import com.mitocode.shop.model.customer.CustomerId;
import com.mitocode.shop.model.product.ProductId;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import static com.mitocode.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;
import static com.mitocode.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;
import static com.mitocode.shop.adapter.in.rest.common.ProductIdParser.parseProductId;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class AddToCartController {

    private final AddToCartUseCase addToCartUseCase;

    @POST
    @Path("/{customerId}/line-items")
    public CartWebModel addLineItem( @PathParam("customerId") String customerIdString,
                                     @QueryParam("productId") String productIdString,
                                     @QueryParam("quantity") int quantity){
        CustomerId customerId = parseCustomerId(customerIdString);
        ProductId productId = parseProductId(productIdString);

        try{
            Cart cart = addToCartUseCase.addToCart(customerId, productId, quantity);
            return CartWebModel.fromDomainModel(cart);
        }catch (ProductNotFoundException e){
            throw clientErrorException(Response.Status.BAD_REQUEST, "The requested product does not exist");
        }catch (NotEnoughItemsInStockException e){
            throw clientErrorException(
                    Response.Status.BAD_REQUEST, "Only %d items in stock".formatted(e.itemInStock()));
        }
    }


}
