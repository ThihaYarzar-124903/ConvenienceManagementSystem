package conveniencemanagementsystem.models;

public class SalesDetails {
	 
	private int id;
	private int qty;
	private int products_id;
	private int discounts_id;
	 
	public  SalesDetails() {}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getProducts_id() {
		return products_id;
	}

	public void setProducts_id(int products_id) {
		this.products_id = products_id;
	}

	public int getDiscounts_id() {
		return discounts_id;
	}

	public void setDiscounts_id(int discounts_id) {
		this.discounts_id = discounts_id;
	}
	

	
 
}
