package com.user.service.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Rating {
    private String ratingId;
    private String hotelId;
    private String userId;
    private Integer rating;
    private String feedback;

    private Hotel hotel;
}
