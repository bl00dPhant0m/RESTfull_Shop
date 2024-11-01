package org.spring.restfull_shop.service.product;

import org.spring.restfull_shop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);

    Optional<Product> findProductById(long id);

    Product updateProduct(long id, Product product);

    void deleteProduct(long id);

    List<Product> getAllProducts();

}
