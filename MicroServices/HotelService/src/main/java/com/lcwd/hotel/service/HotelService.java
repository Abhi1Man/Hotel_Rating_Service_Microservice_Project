package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    //create
    Hotel create(Hotel hotel);
    //getAll
    List<Hotel> getAll();
    //get single
    Hotel get(String id);
}
