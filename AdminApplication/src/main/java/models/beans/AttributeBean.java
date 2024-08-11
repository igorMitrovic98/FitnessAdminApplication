package models.beans;

import java.io.Serializable;
import java.util.List;

import models.daos.AttributeDAO;
import models.dtos.AttributeDTO;

public class AttributeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7146138562002140541L;
	public AttributeBean() {
		
	}
	public List<AttributeDTO> getAllAttributes(String categoryName) {
		return AttributeDAO.selectAll(categoryName);
	}
	public int addNewAttribute(AttributeDTO attribute) {
		return AttributeDAO.insert(attribute);
	}
	public AttributeDTO getAttributeName(String name) {
		return AttributeDAO.getAttributeByName(name);
	}
	
	public int deleteAttribute(AttributeDTO attribute) {
		return AttributeDAO.delete(attribute);
	}
}
