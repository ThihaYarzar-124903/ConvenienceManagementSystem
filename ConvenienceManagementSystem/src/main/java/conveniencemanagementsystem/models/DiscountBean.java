package conveniencemanagementsystem.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class DiscountBean {
	private int id;
	@NotEmpty(message = "Enter dicount name")
	private String name;
	@NumberFormat(style=Style.PERCENT)
	private int discount_percent;
	
	@NotNull(message = "Enter from date")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid from date format. Use yyyy-MM-dd")
	private String from_date;
	@NotNull(message = "Enter to date")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid to date format. Use yyyy-MM-dd")
	private String to_date;

	private List<ProductBean> products = new ArrayList<ProductBean>();
	
	private String product_names;
	
	public DiscountBean() {}
	
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
	
	public String getProduct_names() {
		return product_names;
	}
	public void setProduct_names(String product_names) {
		this.product_names = product_names;
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
	
	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}

}
