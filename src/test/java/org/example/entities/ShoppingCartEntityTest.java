package org.example.entities;

import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartEntityTest {

    private StoreImpl store;

    private ShoppingCartEntity shoppingCart;
    private Map<String, Integer> positions;


    private User user;


    @BeforeEach
    void setUp() {
        user = new User(1L, "Dima");
        store = mock(StoreImpl.class);
        positions = new HashMap<>();
        when(store.getPositions()).thenReturn(positions);
        shoppingCart = new User(2L, "Sasha").getShoppingCarts().get(0);
    }

    @Test
    void addPositionToCart() {
        shoppingCart.getShoppingCart().put("merch1", 1);
        boolean expected = shoppingCart.getShoppingCart().containsKey("merch1");

        user.getCurrentShoppingCart().addPositionToCart("merch1", 1, store);

        boolean actual = user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1")
                && positions.get("merch1") == 9;

        assertEquals(expected, actual);
    }

    @Test
    void deletePositionFromCart() {
        positions.put("merch1", 10);
        user.getCurrentShoppingCart().addPositionToCart("merch1", 1, store);

        boolean expected = user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1")
                && positions.get("merch1") == 9;

        user.getCurrentShoppingCart().deletePositionFromCart("merch1",store);

        boolean actual = !user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1")
                && positions.get("merch1") == 10;

        assertEquals(expected, actual);

    }

    @Test
    void updatePositionInCart() {
    }

    @Test
    void emptyCart() {
    }

    @Test
    void checkMerchInCart() {
    }
}