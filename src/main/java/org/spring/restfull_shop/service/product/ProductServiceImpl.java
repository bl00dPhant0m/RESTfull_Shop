package org.spring.restfull_shop.service.product;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;
import org.spring.restfull_shop.entity.Product;
import org.spring.restfull_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    final private ProductRepository productRepository;


    @Override
    public Product addProduct(Product product) {
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
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        Basket basket = product.getBasket();
        if (basket != null) {
            basket.getProducts().remove(product);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
