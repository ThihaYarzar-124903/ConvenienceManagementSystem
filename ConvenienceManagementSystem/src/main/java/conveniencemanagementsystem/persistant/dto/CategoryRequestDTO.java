package conveniencemanagementsystem.persistant.dto;

public class CategoryRequestDTO {
	private int id;
	private String name;
	
	public CategoryRequestDTO() {}
	
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
