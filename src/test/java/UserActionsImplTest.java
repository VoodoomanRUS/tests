import org.example.User;
import org.example.entities.ShoppingCartEntity;
import org.example.entities.StoreImpl;
import org.example.entities.UserActionsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserActionsImplTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    private StoreImpl store;

    private UserActionsImpl userActions;

    private User user;


    @BeforeEach
    public void setUp() {
        user = new User(1L, "Dima");
        userActions = new UserActionsImpl(user);
        store = new StoreImpl();
        System.setOut(new PrintStream(output));

    }


    @Test
    void findAllStoredMerch() {
        store.setPosition("merch1", 1);
        store.setPosition("merch2", 2);

        userActions.findAllStoredMerch(store);


        assertEquals("merch1, 1 единиц на складе." + System.lineSeparator()
                        + "merch2, 2 единиц на складе." + System.lineSeparator()
                , output.toString());

    }

    @Test
    void findAllShoppingCartsIds() {
        List<Long> expected = new ArrayList<>();
        expected.add(1L);
        expected.add(2L);

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
        assertTrue(user.getCurrentShoppingCart().getShoppingCart().isEmpty());

    }

    @Test
    void createNewShoppingCart() {

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

        userActions.removeShoppingCart(1L);

        assertEquals(expected, userActions.findAllShoppingCartsIds());

    }

    @Test
    void pickShoppingCart() {
        long expected = 3L;
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        System.out.println(user.getShoppingCarts());
        userActions.pickShoppingCart(3L);
        System.out.println(user.getCurrentShoppingCart().getShoppingCartId());

        assertEquals(expected, user.getCurrentShoppingCart().getShoppingCartId());
    }

    @Test
    void pickNotExistingShoppingCart() {
        long expected = 7L;
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        user.getShoppingCarts().add(new ShoppingCartEntity(user));
        userActions.removeShoppingCart(3L);
        System.out.println(user.getShoppingCarts());
        userActions.pickShoppingCart(3L);
        System.out.println(user.getCurrentShoppingCart().getShoppingCartId());

        assertEquals(expected, user.getCurrentShoppingCart().getShoppingCartId());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
