package com.user.service.userservice.external.services;

import com.user.service.userservice.models.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRatingByUserId(@PathVariable String userId);
}
