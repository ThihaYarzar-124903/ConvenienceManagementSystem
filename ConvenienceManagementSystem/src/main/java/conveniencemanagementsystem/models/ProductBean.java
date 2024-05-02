package conveniencemanagementsystem.models;

import java.sql.Blob;
import java.util.Objects;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ProductBean {
	public int id;
	@NotEmpty(message = "Enter Product name")
	private String name;
	//@NotEmpty(message = "Enter  product code")
	private String product_code;
	private int products_categories_id;
	private String category_name;
	
	@NotNull(message = "Product price cannot be null")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @DecimalMax(value = "1000000.00", message = "Price must be less than 1,000,000.00")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
	private double original_price;
	@NotNull(message = "Product price cannot be null")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @DecimalMax(value = "1000000.00", message = "Price must be less than 1,000,000.00")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
	private double sold_price;
	@Min(value = 1, message = "Quantity must be at least 1")
	private int product_qty;
	
	@NotBlank(message="Enter product detail")
	private String product_detail;
	
	private MultipartFile multipart;
	private Blob image_blob;
	private String product_image;
	
	public MultipartFile getMultipart() {
		return multipart;
	}

	public void setMultipart(MultipartFile multipart) {
		this.multipart = multipart;
	}

	public Blob getImage_blob() {
		return image_blob;
	}

	public void setImage_blob(Blob image_blob) {
		this.image_blob = image_blob;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public ProductBean() {}
	
	public String getProduct_detail() {
		return product_detail;
	}

	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
	}

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
	public int getProducts_categories_id() {
		return products_categories_id;
	}
	public void setProducts_categories_id(int products_categories_id) {
		this.products_categories_id = products_categories_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	public double getSold_price() {
		return sold_price;
	}
	public void setSold_price(double sold_price) {
		this.sold_price = sold_price;
	}
	public int getProduct_qty() {
		return product_qty;
	}
	public void setProduct_qty(int product_qty) {
		this.product_qty = product_qty;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBean other = (ProductBean) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
