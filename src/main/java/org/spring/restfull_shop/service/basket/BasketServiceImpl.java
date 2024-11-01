package org.spring.restfull_shop.service.basket;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;
import org.spring.restfull_shop.entity.Product;
import org.spring.restfull_shop.repository.BasketRepository;
import org.spring.restfull_shop.service.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductService productService;

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
    public Basket addProduct(long basketId, Product product) {
        Basket basket = findBasketById(basketId);
        product.setBasket(basket);
        basket.getProducts().add(product);

        productService.addProduct(product);
        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteProduct(long basketId, long productId) {
        Basket basket = findBasketById(basketId);
        Product product = productService.findProductById(productId)
                .orElseThrow(()-> new RuntimeException("Product with id " + productId + " not found"));

        if (!basket.getProducts().contains(product)) {
            throw new IllegalStateException("Продукт не принадлежит этой корзине");
        }

        product.setBasket(null);
        basket.getProducts().remove(product);

        productService.deleteProduct(productId);
        return basketRepository.save(basket);

    }

    @Override
    public Basket findBasketById(long id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Basket with id"  + id +" not found"));
    }


    @Override
    public List<Basket> getAllBasket() {
        return basketRepository.findAll();
    }

    @Override
    public Basket cleanBasket(long id) {
        Basket basket = findBasketById(id);
        List<Product> products = basket.getProducts();
        basket.setProducts(null);

        for (Product product : products) {
            product.setBasket(null);
            productService.deleteProduct(product.getId());
        }

        return basketRepository.save(basket);
    }
}
