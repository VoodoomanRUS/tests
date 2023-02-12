package org.example.entities;

import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserActionsImplTest {

    private StoreImpl store;

    private Map<String, Integer> positions;

    private UserActionsImpl userActions;

    private User user;

    private List<ShoppingCartEntity> shoppingCarts;

    private ShoppingCartEntity currentShoppingCart;

    @BeforeEach
    public void setUp() {
        user = mock(User.class);
        shoppingCarts = new ArrayList<>();

        when(user.getUserId()).thenReturn(1L);
        when(user.getUserName()).thenReturn("Dima");
        when(user.getShoppingCarts()).thenReturn(shoppingCarts);
        when(user.getCurrentShoppingCart()).thenReturn(currentShoppingCart);

        store = mock(StoreImpl.class);
        positions = new HashMap<>();
        when(store.getPositions()).thenReturn(positions);

        userActions = new UserActionsImpl(user);
        userActions.createNewShoppingCart();
//        userActions.createNewShoppingCart();
//        userActions.createNewShoppingCart();
//        userActions.createNewShoppingCart();
//        userActions.createNewShoppingCart();

        currentShoppingCart = shoppingCarts.get(0);
    }


    @Test
    void findAllStoredMerch() {
        store.setPosition("merch1", 1);
        store.setPosition("merch2", 1);
        store.setPosition("merch3", 1);

        userActions.findAllStoredMerch(store);
        verify(store, times(1)).getPositions();
    }

    @Test
    void findAllShoppingCartsIds() {
        List<Long> expected = new ArrayList<>();
        expected.add(1L);
        expected.add(2L);

        userActions.getUser().getShoppingCarts().add(new ShoppingCartEntity(user));
        userActions.getUser().getShoppingCarts().add(new ShoppingCartEntity(user));

        assertEquals(expected, userActions.findAllShoppingCartsIds());

    }

    @Test
    void checkout() {

        user.getCurrentShoppingCart().getShoppingCart().put("merch1", 1);
        user.getCurrentShoppingCart().getShoppingCart().put("merch2", 1);
        user.getCurrentShoppingCart().getShoppingCart().put("merch3", 1);
        System.out.println(user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1"));
        userActions.checkout();
        verify(user, times(1)).getShoppingCarts();
        assertTrue(user.getCurrentShoppingCart().getShoppingCart().isEmpty());

    }

    @Test
    void createNewShoppingCart() {

        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        assertEquals(5, user.getShoppingCarts().size());

    }

    @Test
    void removeShoppingCart() {
        List<Long> expected = new ArrayList<>();
        expected.add(2L);
        expected.add(3L);
        expected.add(4L);
        expected.add(5L);

        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();
        userActions.createNewShoppingCart();

        userActions.removeShoppingCart(1L);

        assertEquals(expected, userActions.findAllShoppingCartsIds());

    }

    @Test
    void pickShoppingCart() {
//        userActions.findAllShoppingCartsIds();
//        System.out.println(user.getCurrentShoppingCart().getShoppingCartId());
//        userActions.removeShoppingCart(3L);
//        userActions.pickShoppingCart(3L);
//        System.out.println(user.getCurrentShoppingCart().getShoppingCartId());
//        userActions.findAllShoppingCartsIds();
//        assertEquals(3L, user.getCurrentShoppingCart().getShoppingCartId());

    }
}
