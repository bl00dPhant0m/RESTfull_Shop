package org.spring.restfull_shop.controller;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable Long id, @RequestBody Basket basket) {
        return ResponseEntity.ok(basketService.updateBasket(id,basket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Basket> deleteBasket(@PathVariable Long id) {
        basketService.deleteBasket(id);
        return ResponseEntity.ok().build();
    }
}
