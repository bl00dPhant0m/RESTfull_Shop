package org.spring.restfull_shop.service.basket;


import org.spring.restfull_shop.entity.Basket;

import java.util.List;

public interface BasketService {
    Basket addBasket(Basket basket);
    Basket findBasketById(long id);

    Basket updateBasket(long id, Basket basket);

    void deleteBasket(long id);

    List<Basket> getAllBasket();
}
