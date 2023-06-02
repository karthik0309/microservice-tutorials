package com.hotel.service.hotelservice.service.hotel;

import com.hotel.service.hotelservice.exceptions.ResourceNotFoundException;
import com.hotel.service.hotelservice.models.Hotel;
import com.hotel.service.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String uniqueId = UUID.randomUUID().toString();
        hotel.setHotelId(uniqueId);
        return hotelRepository.saveAndFlush(hotel);
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found"));
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
