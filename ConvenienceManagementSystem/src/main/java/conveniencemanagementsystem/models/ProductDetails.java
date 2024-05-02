package conveniencemanagementsystem.models;

public class ProductDetails {
	
	private int id;
	private String name;
	private double sold_price;
	private int product_discount;
	private int discount_id;
	
	private int product_qty;
	
	public ProductDetails() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(int product_qty) {
		this.product_qty = product_qty;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSold_price() {
		return sold_price;
	}
	public void setSold_price(double sold_price) {
		this.sold_price = sold_price;
	}
	public int getDiscount_id() {
		return discount_id;
	}

	public void setDiscount_id(int discount_id) {
		this.discount_id = discount_id;
	}
	public int getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}

}
