package org.spring.restfull_shop.service.basket;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;
import org.spring.restfull_shop.entity.Product;
import org.spring.restfull_shop.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;

    @Override
    public Basket addBasket(Basket basket) {
        if (basket != null && basket.getProducts() != null) {
            for (Product product : basket.getProducts()) {
                product.setBasket(basket);
            }
        }
        return basketRepository.save(basket);
    }

    @Override
    public Basket findBasketById(long id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Basket with id"  + id +" not found"));
    }

    @Override
    public Basket updateBasket(long id, Basket updatedBasket) {
        return basketRepository.findById(id)
                .map(basket -> {
                    basket.setProducts(updatedBasket.getProducts());
                    return basketRepository.save(basket);
                }).orElseThrow(() -> new RuntimeException("Basket with id " + id + " not found"));
    }

    @Override
    public void deleteBasket(long id) {
        Basket basket = findBasketById(id);
        basketRepository.delete(basket);
    }

    @Override
    public List<Basket> getAllBasket() {
        return basketRepository.findAll();
    }
}
