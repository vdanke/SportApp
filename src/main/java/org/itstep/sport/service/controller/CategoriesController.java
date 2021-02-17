package org.itstep.sport.service.controller;

import org.itstep.sport.service.model.Category;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Category>> getAllCategories() {
        Category first = new Category(1L, "Sport", null);
        Category second = new Category(2L, "Eat", null);
        return ResponseEntity.ok(new ArrayList<>() {{
            add(first);
            add(second);
        }});
    }
}
