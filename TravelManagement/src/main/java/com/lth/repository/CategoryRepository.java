package com.lth.repository;

import com.lth.pojos.Category;

import java.util.List;

public interface CategoryRepository {
	List<Category> getCategories();
	Category getCategoryByID(int id);
	boolean addCategory(Category category);
	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);
}
