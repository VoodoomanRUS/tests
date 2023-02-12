package org.example.interfaces;

import org.example.User;
import org.example.entities.ShoppingCartEntity;
import org.example.entities.StoreImpl;

import java.util.ArrayList;
import java.util.List;

public interface UserActions {

    /*
     * Ищем все товары на складе
     * */
    void findAllStoredMerch(StoreImpl store);

    List<Long> findAllShoppingCartsIds();
    void checkout();
    void createNewShoppingCart();
    void removeShoppingCart(Long id);
    void pickShoppingCart(Long id);
}
