package org.example.entities;

import lombok.Getter;
import org.example.User;
import org.example.interfaces.ShoppingCart;

import java.util.HashMap;


@Getter
public class ShoppingCartEntity implements ShoppingCart {

    private User user;
    private Long shoppingCartId;
    private HashMap<String, Integer> shoppingCart = new HashMap<>();

    public ShoppingCartEntity(User user) {
        this.user = user;
        this.shoppingCartId = createShoppingCartId(user);
    }

    /*
     * назначаем идентификатор новой корзине
     * */
    private Long createShoppingCartId(User user) {
        if (user.getShoppingCarts().isEmpty()) {
            return 1L;
        } else {
            return user.getShoppingCarts().get(user.getShoppingCarts().size() - 1).getShoppingCartId() + 1L;
        }
    }

    /*
     * добавляем позицию в корзину и одновременно удаляем со склада
     * */
    @Override
    public void addPositionToCart(String merchName, Integer count, StoreImpl store) {
        if (store.getPositions().containsKey(merchName) && store.getPositions().get(merchName) > 0) {
            shoppingCart.put(merchName, count);
            store.getPositions().replace(merchName, store.getPositions().get(merchName) - count);
            System.out.println("Товар " + merchName + " добавлен в корзину пользователя " + user.getUserId());
        } else {
            System.out.println("Товар отсутствует на складе!!!");
        }
    }

    /*
     * удаляем позицию из корзины и добавляем товары обратно на склад
     * */
    @Override
    public void deletePositionFromCart(String merchName, StoreImpl store) {
        if (shoppingCart.containsKey(merchName)) {
            store.getPositions().put(merchName, shoppingCart.get(merchName) + store.getPositions().get(merchName));
            shoppingCart.remove(merchName);
            System.out.println("Товар " + merchName + " удалён из корзины пользователя " + user.getUserId());
        } else {
            System.out.println("В корзине пользователя " + user.getUserId() + " отсутствует данный товар!");
        }
    }

    /*
     * обновляем товар в корзине
     * */
    @Override
    public void updatePositionInCart(String merchName, Integer newCount, StoreImpl store) {
        if (store.getPositions().get(merchName) >= newCount) {
            shoppingCart.put(merchName, newCount);
            System.out.println("Количество товара " + merchName + " у пользователя " + user.getUserId() + " изменено!!!");
        }
    }

    /*
     * удаляем все товары из корзины добавляя их обратно на склад
     * */
    @Override
    public void emptyCart(StoreImpl store) {
        store.getPositions().putAll(shoppingCart);
        shoppingCart.clear();
        System.out.println("Корзина пользователя " + user.getUserId() + " очищена!!!");
    }

    /*
     * выводим список товаров из корзины пользователя
     * */
    @Override
    public void checkMerchInCart() {
        if (!shoppingCart.isEmpty()) {
            shoppingCart.forEach((key, value) -> {
                System.out.print(key + ", ");
                System.out.println(value + " единиц в корзине пользователя " + user.getUserId());
            });
        } else {
            System.out.println("В корзине пользователя " + user.getUserId() + " отсутствуют товары!!!");
        }
    }
}
