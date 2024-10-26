package org.spring.restfull_shop.controller;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.User;
import org.spring.restfull_shop.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    final private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@RequestParam long id) {
       return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        Optional<User> userById = userService.findUserById(id);
        if (!userById.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PutMapping("/{userId}/basket/{basketId}")
    public ResponseEntity<User> addBasket(@PathVariable long userId, @PathVariable long basketId) {
        return ResponseEntity.ok(userService.setBasket(userId, basketId));
    }

}
