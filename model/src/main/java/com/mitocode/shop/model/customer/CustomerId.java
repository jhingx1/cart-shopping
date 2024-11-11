package com.mitocode.shop.model.customer;

import com.mitocode.shop.model.product.ProductId;

public record CustomerId(int value) {
    public CustomerId{
        if(value<1){
            throw new IllegalArgumentException("'value' must be a positive integer");
        }
    }
    public static void main(String[] args) {
        System.out.println(new CustomerId(10));
    }
}
