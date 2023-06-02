package com.user.service.userservice.service.user;

import com.user.service.userservice.models.Rating;
import com.user.service.userservice.models.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

    Rating createUserRating(Rating rating);
}
