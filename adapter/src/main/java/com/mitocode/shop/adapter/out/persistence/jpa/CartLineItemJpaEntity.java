package com.mitocode.shop.adapter.out.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CartLineItem")
@Getter
@Setter
public class CartLineItemJpaEntity {

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    private CartJpaEntity cart;

    @ManyToOne//muchos carritos puede tener el mismo productos
    private ProductJpaEntity product;

    private int quantity;
}
