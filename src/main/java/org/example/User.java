package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.ShoppingCartEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {


    /*
     *    айди пользователя
     * */
    private Long userId;
    /*
     * имя пользователя
     * */
    private String userName;
    /*
     * список корзин пользователя
     * */
    private List<ShoppingCartEntity> shoppingCarts = new ArrayList<>();
    /*
     * текущая корзина пользователя
     * */
    @Setter
    private ShoppingCartEntity currentShoppingCart;

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        shoppingCarts.add(new ShoppingCartEntity(this));
        this.currentShoppingCart = this.getShoppingCarts().get(0);
    }

}
