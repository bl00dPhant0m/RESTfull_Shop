package org.spring.restfull_shop.service.basket;


import org.spring.restfull_shop.entity.Basket;
import org.spring.restfull_shop.entity.Product;

import java.util.List;

public interface BasketService {
    Basket addBasket(Basket basket);

    Basket findBasketById(long id);

    Basket cleanBasket(long id);

    List<Basket> getAllBasket();

    Basket addProduct(long basketId, Product product);

    Basket deleteProduct(long basketId, long productId);
}
