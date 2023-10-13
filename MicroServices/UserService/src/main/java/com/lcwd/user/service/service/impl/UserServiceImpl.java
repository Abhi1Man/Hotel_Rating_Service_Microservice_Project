package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.Entities.Hotel;
import com.lcwd.user.service.Entities.Rating;
import com.lcwd.user.service.Entities.User;
import com.lcwd.user.service.exception.ResourseNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    //    private Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User with given id is not found on server !! : " + userId));
        //http://localhost:9092/ratings/users/2fa681b5-18d4-4cfb-8668-eb06960b03a6

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        System.out.println("Rating of users "+ ratingsOfUser.toString());

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList =ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = hotelService.getHotel("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            System.out.println("Hotel "+hotel);
            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;


        //Assignment(delete and update)
    }
}
