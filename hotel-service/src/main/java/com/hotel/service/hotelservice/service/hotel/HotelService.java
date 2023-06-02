package com.hotel.service.hotelservice.service.hotel;

import com.hotel.service.hotelservice.models.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);

    Hotel getHotelById(String hotelId);

    List<Hotel> getAll();
}