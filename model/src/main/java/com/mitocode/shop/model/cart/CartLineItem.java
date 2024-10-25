package com.mitocode.shop.model.cart;

import com.mitocode.shop.model.money.Money;
import com.mitocode.shop.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class CartLineItem {

    private final Product product;
    private int quantity;

    //incrementar items
    public void increaseQuantityBy(int augend,int itemInStock) throws NotEnoughItemsInStockException{
            if(augend < 1){
                throw new IllegalArgumentException("You must add at least one item");
            }
            int newQuantity = quantity + augend;
            if(itemInStock < newQuantity){
                throw new NotEnoughItemsInStockException("Product %s has less items ins stock (%d) than the request total quantity (%d)"
                        .formatted(product.id(),product.itemInStock(), newQuantity),product.itemInStock());
            }
            this.quantity = newQuantity;
    }

    public Money subTotal(){
        return product.price().multiply(quantity);
    }

}
