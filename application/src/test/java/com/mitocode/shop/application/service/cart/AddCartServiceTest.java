package com.mitocode.shop.application.service.cart;

import com.mitocode.shop.application.port.out.persistence.CartRepository;
import com.mitocode.shop.application.port.out.persistence.ProductRepository;
import com.mitocode.shop.model.customer.CustomerId;
import com.mitocode.shop.model.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static com.mitocode.shop.model.money.TestMoneyFactory.euros;
import static com.mitocode.shop.model.product.TestProductFactory.createTestProduct;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddCartServiceTest {

    private static final CustomerId TEST_CUSTOMER_ID = new CustomerId(61157);
    private static final Product TEST_PRODUCT_1 = createTestProduct(euros(19,99));
    private static final Product TEST_PRODUCT_2 = createTestProduct(euros(25,99));

    private final CartRepository cartRepository = mock(CartRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final AddToCartService addToCartService = new AddToCartService(cartRepository,productRepository);

    @BeforeEach
    void initTestDouble(){
        when(productRepository.findById(TEST_PRODUCT_1.id())).thenReturn(Optional.of(TEST_PRODUCT_1));
        when(productRepository.findById(TEST_PRODUCT_2.id())).thenReturn(Optional.of(TEST_PRODUCT_2));
    }

}
