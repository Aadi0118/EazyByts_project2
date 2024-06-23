package com.adityasinha.controller;

import com.adityasinha.model.Category;
import com.adityasinha.model.User;
import com.adityasinha.repository.UserRepository;
import com.adityasinha.service.CategoryService;
import com.adityasinha.service.UserService;
import com.adityasinha.service.UserService;
import com.adityasinha.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization")String jwt)throws Exception{
        User user= userServiceImp.findUserByJwtToken(jwt);

        Category createdCategory=categoryService.createCategory(category.getName(),user.getId());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(
                                                   @RequestHeader("Authorization")String jwt)throws Exception{
        User user= userServiceImp.findUserByJwtToken(jwt);

        List<Category> categories=categoryService.findCategoryByRestaurantId(user.getId());
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
