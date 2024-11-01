package org.spring.restfull_shop.controller;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;

import org.spring.restfull_shop.entity.Product;
import org.spring.restfull_shop.service.basket.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/baskets")
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/add")
    public ResponseEntity<Basket> addBasket(@RequestBody Basket basket) {
        return ResponseEntity.ok(basketService.addBasket(basket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasket(@PathVariable Long id) {
        return ResponseEntity.ok(basketService.findBasketById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Basket>> getAllBasket() {
        return ResponseEntity.ok(basketService.getAllBasket());
    }


    @DeleteMapping("/{basketId}/products/{productId}")
    public ResponseEntity<Basket> deleteProductFromBasket(@PathVariable Long basketId, @PathVariable Long productId) {
        return ResponseEntity.ok(basketService.deleteProduct(basketId, productId));
    }

    @PostMapping("/{basketID}/products")
    public ResponseEntity<Basket> addProductToBasket(@PathVariable Long basketID, @RequestBody Product product) {
        return ResponseEntity.ok(basketService.addProduct(basketID, product));
    }

    @PutMapping("/clean/{id}")
    public ResponseEntity<Basket> cleanBasket(@PathVariable Long id) {
        return ResponseEntity.ok(basketService.cleanBasket(id));
    }

}
