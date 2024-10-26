package org.spring.restfull_shop.service.user;

import lombok.RequiredArgsConstructor;
import org.spring.restfull_shop.entity.Basket;
import org.spring.restfull_shop.entity.Product;
import org.spring.restfull_shop.entity.User;
import org.spring.restfull_shop.repository.UserRepository;
import org.spring.restfull_shop.service.basket.BasketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private BasketService basketService;

    @Override
    public User addUser(User user) {
        Basket basket = user.getBasket();


        if (basket != null && basket.getProducts() != null) {
            for (Product product : basket.getProducts()) {
                product.setBasket(basket);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(long id, User updatedUser) {
       return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setBasket(updatedUser.getBasket());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));


    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User setBasket(long id, long basketId) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id " + id + " not found"));
        Basket basket = basketService.findBasketById(basketId);

        user.setBasket(basket);
        return userRepository.save(user);
    }
}
