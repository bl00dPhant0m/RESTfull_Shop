package org.spring.restfull_shop.service.user;

import org.spring.restfull_shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    Optional<User> findUserById(long id);

    User updateUser(long id, User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User setBasket(long userId, long basketId);
}
