package org.spring.restfull_shop.service.product;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;
import org.spring.restfull_shop.entity.Product;
import org.spring.restfull_shop.repository.ProductRepository;
import org.spring.restfull_shop.service.basket.BasketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    final private ProductRepository productRepository;
    final private BasketService basketService;


    @Override
    public Product addProduct(Product product, long basketId) {
        System.out.println(basketId);
        Basket basket = basketService.findBasketById(basketId);
        basket.getProducts().add(product);
        product.setBasket(basket);
        basketService.updateBasket(basketId, basket);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
