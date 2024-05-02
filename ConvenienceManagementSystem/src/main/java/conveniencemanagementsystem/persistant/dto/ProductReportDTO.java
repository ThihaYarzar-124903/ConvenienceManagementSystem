package conveniencemanagementsystem.persistant.dto;

public class ProductReportDTO {
    private String productName;
    private double discountPercent;
    private int qty;
    
    public ProductReportDTO() {}
    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
