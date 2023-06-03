package com.user.service.userservice.controllers;

import com.user.service.userservice.models.Rating;
import com.user.service.userservice.models.User;
import com.user.service.userservice.service.user.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1= userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    public ResponseEntity<User> ratingHotelFallback(String userId,Exception exception){
        LOGGER.info("Rating hotel fallback has been executed");
        User user = User.builder()
                .name("DUMMY")
                .about("Service down because of: "+exception.getMessage())
                .build();

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/rating")
    public ResponseEntity<Rating> createRatingByUser(@RequestBody Rating rating){
        Rating rating1 = userService.createUserRating(rating);

        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
}
