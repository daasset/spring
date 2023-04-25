package kz.bitlab.trello.service;

import kz.bitlab.trello.entity.Category;
import kz.bitlab.trello.entity.Folder;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    public List<Category> getAllCategories();
    Category addCategory(String name);
}
