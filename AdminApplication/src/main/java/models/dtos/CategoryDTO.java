package models.dtos;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2902247860973029198L;
	private String name;
	public CategoryDTO(){
		
	}
	public CategoryDTO(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
