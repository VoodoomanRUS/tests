package org.example.interfaces;

import org.example.entities.StoreImpl;

public interface ShoppingCart {

    /*
     * добавляем позицию в корзину и одновременно удаляем со склада
     * */
    void addPositionToCart(String merchName, Integer count, StoreImpl store);


    /*
     * удаляем позицию из корзины и добавляем товары обратно на склад
     * */
    void deletePositionFromCart(String merchName, StoreImpl store);


    /*
     * обновляем товар в корзине
     * */
    void updatePositionInCart(String merchName, Integer newCount, StoreImpl store);

    void emptyCart(StoreImpl store);

    void checkMerchInCart();
}
