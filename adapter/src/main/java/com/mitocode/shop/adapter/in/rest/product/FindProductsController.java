package com.mitocode.shop.adapter.in.rest.product;

import com.mitocode.shop.application.port.in.product.FindProductsUseCase;
import com.mitocode.shop.model.product.Product;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.mitocode.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class FindProductsController {

    private final FindProductsUseCase findProductsUseCase;

    @GET
    public List<ProductInListWebModel> findProducts(@QueryParam("query") String query){
        if(query==null){
            throw clientErrorException(Response.Status.BAD_REQUEST, "Missing 'query'");
        }
        List<Product> products;
        try {
            products = findProductsUseCase.findByNameOrDescription(query);
        }catch (IllegalArgumentException e){
            throw  clientErrorException(Response.Status.BAD_REQUEST, "Invalid 'query'");
        }
        //cambio de model por modelweb
        return products.stream().map(ProductInListWebModel::fromDomainModel).toList();
    }

}
