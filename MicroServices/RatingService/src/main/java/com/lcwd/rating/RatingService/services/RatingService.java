package com.lcwd.rating.RatingService.services;

import com.lcwd.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating createRating(Rating rating);

    //get All ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
