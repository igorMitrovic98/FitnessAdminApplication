package models.dtos;

import java.io.Serializable;

public class AttributeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4834480838432404201L;
	private String name;
	private String categoryName;
	
	
	public AttributeDTO(String name, String categoryName) {
		super();
		this.name = name;
		this.categoryName = categoryName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
