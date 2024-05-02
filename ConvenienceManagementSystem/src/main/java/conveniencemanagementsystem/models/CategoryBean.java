package conveniencemanagementsystem.models;

import javax.validation.constraints.NotEmpty;

public class CategoryBean {
	private int id;
	@NotEmpty(message = "Enter category name")
	private String name;
	
	public CategoryBean() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
