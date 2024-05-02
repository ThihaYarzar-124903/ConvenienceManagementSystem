package conveniencemanagementsystem.persistant.dto;

import java.util.ArrayList;
import java.util.List;

public class DiscountDTO {
	private int id;
	private String name;
	private int discount_percent;
	private String from_date;
	private String to_date;

	private List<ProductDTO> products = new ArrayList<ProductDTO>();
	
	private String product_name;
	
	public DiscountDTO() {}
	
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
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getDiscount_percent() {
		return discount_percent;
	}
	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	
	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
}
