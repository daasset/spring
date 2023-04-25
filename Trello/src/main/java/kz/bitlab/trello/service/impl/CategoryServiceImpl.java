package kz.bitlab.trello.service.impl;

import kz.bitlab.trello.entity.Category;
import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.repository.CategoryRepository;
import kz.bitlab.trello.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        if (id == null) {
            return null;
        }
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        Category category = categoryRepository.findCategoryByName(name);
        if (category != null) {
            return null;
        }
        category = Category.builder().name(name).build();

        return categoryRepository.save(category);
    }
}
