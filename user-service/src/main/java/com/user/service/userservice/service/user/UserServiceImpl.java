package com.user.service.userservice.service.user;

import com.user.service.userservice.exceptions.ResourceNotFoundException;
import com.user.service.userservice.external.services.HotelService;
import com.user.service.userservice.external.services.RatingService;
import com.user.service.userservice.models.Hotel;
import com.user.service.userservice.models.Rating;
import com.user.service.userservice.models.User;
import com.user.service.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

//    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User createUser(User user) {
        String uniqueId = UUID.randomUUID().toString();
        user.setUserId(uniqueId);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users= userRepository.findAll();

        return users.stream().peek(this::getUserRatings).toList();
    }

    @Override
    public User getUserById(String userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        return getUserRatings(user);
    }

    @Override
    public Rating createUserRating(Rating rating) {
        return ratingService.createRating(rating);
    }

    private User getUserRatings(User user){
        List<Rating> ratings= ratingService.getRatingByUserId(user.getUserId());

        List<Rating> ratingList= ratings.stream().peek(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }).toList();

        user.setRatings(ratingList);
        return user;
    }
}
