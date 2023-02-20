import org.example.User;
import org.example.entities.ShoppingCartEntity;
import org.example.entities.StoreImpl;
import org.example.entities.UserActionsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartEntityTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

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
        System.setOut(new PrintStream(output));
    }


    @Test
    void addPositionToCart() {
        shoppingCart.getShoppingCart().put("merch1", 1);
        boolean expected = shoppingCart.getShoppingCart().containsKey("merch1");
        positions.put("merch1", 10);
        user.getCurrentShoppingCart().addPositionToCart("merch1", 1, store);

        boolean actual = user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1")
                && positions.get("merch1") == 9;

        assertEquals(expected, actual);
    }

    @Test
    void addAbsentPositionToCart() {
        user.getCurrentShoppingCart().addPositionToCart("merch100", 1, store);
        assertEquals("Товар отсутствует на складе!!!" + System.lineSeparator(), output.toString());

    }

    @Test
    void deletePositionFromCart() {
        positions.put("merch1", 10);
        user.getCurrentShoppingCart().addPositionToCart("merch1", 1, store);

        boolean expected = user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1")
                && positions.get("merch1") == 9;

        user.getCurrentShoppingCart().deletePositionFromCart("merch1", store);

        boolean actual = !user.getCurrentShoppingCart().getShoppingCart().containsKey("merch1")
                && positions.get("merch1") == 10;

        assertEquals(expected, actual);

    }

    @Test
    void deleteAbsentPositionFromCart() {
        user.getCurrentShoppingCart().deletePositionFromCart("merch100", store);
        assertEquals("В корзине пользователя 1 отсутствует данный товар!" + System.lineSeparator(), output.toString());
    }

    @Test
    void updatePositionInCart() {
        int expected = 5;

        positions.put("merch1", 10);
        user.getCurrentShoppingCart().addPositionToCart("merch1", 3, store);

        user.getCurrentShoppingCart().updatePositionInCart("merch1", 5, store);

        int actual = user.getCurrentShoppingCart().getShoppingCart().get("merch1");

        assertEquals(expected, actual);
    }

    @Test
    void emptyCart() {
        user.getCurrentShoppingCart().addPositionToCart("merch1", 3, store);

        user.getCurrentShoppingCart().emptyCart(store);

        boolean actual = user.getCurrentShoppingCart().getShoppingCart().isEmpty();

        assertTrue(actual);

    }

    @Test
    void checkMerchInCart() {
        positions.put("merch1", 10);
        user.getCurrentShoppingCart().getShoppingCart().put("merch1", 3);

        user.getCurrentShoppingCart().checkMerchInCart();
        assertEquals("merch1, 3 единиц в корзине пользователя 1" + System.lineSeparator(), output.toString());


    }

    @Test
    void checkMerchInEmptyCart() {
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        UserActionsImpl userActions = new UserActionsImpl(user);
        userActions.pickShoppingCart(2L);
        user.getCurrentShoppingCart().checkMerchInCart();
        assertEquals("В корзине пользователя 1 отсутствуют товары!!!" + System.lineSeparator(), output.toString());

    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }
}