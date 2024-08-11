package models.beans;

import java.io.Serializable;
import java.util.List;

import models.daos.CategoryDAO;
import models.dtos.CategoryDTO;

public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743816195972315080L;
	
	public CategoryBean() {
		
	}
	
	public List<CategoryDTO> getAllCategories() {
		return CategoryDAO.selectAll();
	}
	public int addNewCategory(CategoryDTO category) {
		return CategoryDAO.insert(category);
	}
	public CategoryDTO getCategoryName(String name) {
		return CategoryDAO.getCategoryByName(name);
	}
	public int deleteCategory(CategoryDTO category) {
		return CategoryDAO.delete(category);
	}
}
