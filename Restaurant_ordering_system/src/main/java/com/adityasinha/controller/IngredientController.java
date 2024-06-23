package com.adityasinha.controller;


import com.adityasinha.model.IngredientCategory;
import com.adityasinha.model.IngredientsItem;
import com.adityasinha.request.IngredientCategoryRequest;
import com.adityasinha.request.IngredientRequest;
import com.adityasinha.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService IngredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> categoryResponseEntity(
            @RequestBody IngredientCategoryRequest req)throws Exception{
        IngredientCategory item= IngredientsService.createIngredientCategory(req.getRestaurantId(), req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req) throws Exception {
        IngredientsItem item= IngredientsService.createIngredientsItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id) throws Exception {
        IngredientsItem item= IngredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id) throws Exception {
        List<IngredientsItem> items= (List<IngredientsItem>) IngredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id) throws Exception {
        List<IngredientCategory> items= (List<IngredientCategory>) IngredientsService.findingredientCateforyByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
