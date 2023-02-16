package org.example.entities;

import lombok.Getter;
import org.example.User;
import org.example.interfaces.UserActions;

import java.util.ArrayList;
import java.util.List;


@Getter
public class UserActionsImpl implements UserActions {


    private User user;



    public UserActionsImpl(User user) {
        this.user = user;
//        this.currentShoppingCart = new ShoppingCartEntity(user);
    }


    /*
     * Ищем все товары на складе
     * */
    @Override
    public void findAllStoredMerch(StoreImpl store) {
        store.getPositions().forEach((key, value) -> {
            System.out.print(key + ", ");
            System.out.println(value + " единиц на складе.");
        });
    }

    /*
     * показать список всех корзин пользователя
     * */
    @Override
    public List<Long> findAllShoppingCartsIds() {
        List<Long> ids = new ArrayList<>();
        user.getShoppingCarts().forEach(shoppingCartEntity -> ids.add(shoppingCartEntity.getShoppingCartId()));
        System.out.println("Доступные корзины: " + ids);
        return ids;
    }

    /*
     * эмулируем покупку товаров опустошая корзину
     * */
    @Override
    public void checkout() {
        user.getCurrentShoppingCart().getShoppingCart().clear();
        System.out.println("Все товары из корзины пользователя " + user.getUserId() + " приобретены!");
    }

    /*
     * создаём новую корзину для пользователя
     * */
    @Override
    public void createNewShoppingCart() {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity(user);
        user.getShoppingCarts().add(shoppingCartEntity);
        System.out.println("Создана корзина " + shoppingCartEntity.getShoppingCartId());
    }

    /*
     * удаляем корзину по идентификатору
     * */
    @Override
    public void removeShoppingCart(Long id) {
        user.getShoppingCarts()
                .removeIf(shoppingCartEntity -> shoppingCartEntity.getShoppingCartId().equals(id));
        System.out.println("Удалена корзина " + id);
    }

    /*
     * выбираем корзину по идентификатору для взаимодействия
     * */
    @Override
    public void pickShoppingCart(Long id) {
        user.setCurrentShoppingCart(
        user.getShoppingCarts()
                .stream()
                .filter(shoppingCartEntity ->
                        shoppingCartEntity
                                .getShoppingCartId()
                                .equals(id))
                .findFirst()
                .orElseGet(() -> new ShoppingCartEntity(user))
        );
    }

}
