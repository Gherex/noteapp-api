package com.gherex.noteapp.services;

import com.gherex.noteapp.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Category createCategory(Category category);

    boolean existsById(Long id);

    void deleteCategory(Long id);

    Optional<Category> updateCategory(Long id, Category category);
}
