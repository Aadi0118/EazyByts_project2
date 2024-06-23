package com.adityasinha.controller;


import com.adityasinha.model.Food;
import com.adityasinha.model.Restaurant;
import com.adityasinha.model.User;
import com.adityasinha.request.CreateFoodRequest;
import com.adityasinha.response.MessageResponse;
import com.adityasinha.service.FoodService;
import com.adityasinha.service.RestaurantService;
import com.adityasinha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private com.adityasinha.service.UserService userService;


    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurnat= restaurantService.findRestaurantById(req.getRestaurantId());
        Food food=foodService.createFood(req,req.getCategory(),restaurnat);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                     @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse res= new MessageResponse();
        res.setMessage("food deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailibiltyStatus(@PathVariable Long id,
                                                     @RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailibiltyStatus(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
