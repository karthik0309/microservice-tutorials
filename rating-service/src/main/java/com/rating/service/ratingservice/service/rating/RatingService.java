package com.rating.service.ratingservice.service.rating;

import com.rating.service.ratingservice.models.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getAllRatings();
    List<Rating> getAllRatingsByUserId(String userId);
    List<Rating> getAllRatingsByHotelId(String hotelId);
}
