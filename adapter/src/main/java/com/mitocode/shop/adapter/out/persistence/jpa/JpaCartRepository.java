package com.mitocode.shop.adapter.out.persistence.jpa;

import com.mitocode.shop.application.port.out.persistence.CartRepository;
import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.customer.CustomerId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

public class JpaCartRepository implements CartRepository{

    private final EntityManagerFactory entityManagerFactory;

    public JpaCartRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Cart cart) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            entityManager.merge(CartMapper.toJpaEntity(cart));
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public Optional<Cart> findByCustomerId(CustomerId customerId) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            CartJpaEntity cartJpaEntity = entityManager.find(CartJpaEntity.class, customerId.value());
            return CartMapper.toModelEntityOptional(cartJpaEntity);
        }
    }

    @Override
    public void deleteByCustomerId(CustomerId customerId) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            CartJpaEntity cartJpaEntity = entityManager.find(CartJpaEntity.class, customerId.value());

            if(cartJpaEntity != null){
                entityManager.remove(cartJpaEntity);
            }

            entityManager.getTransaction().commit();

        }
    }
}
