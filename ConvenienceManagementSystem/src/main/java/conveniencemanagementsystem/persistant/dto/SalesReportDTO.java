package conveniencemanagementsystem.persistant.dto;

import java.sql.Date;
import java.util.List;

public class SalesReportDTO {
    private int saleId;
    private String username;
    private Date purchaseDate;
    private Double totalAmount;
    private int salesDetailsId;
    private List<ProductReportDTO> products;
    
    public SalesReportDTO() {}
    
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getSalesDetailsId() {
		return salesDetailsId;
	}
	public void setSalesDetailsId(int salesDetailsId) {
		this.salesDetailsId = salesDetailsId;
	}

	public List<ProductReportDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductReportDTO> products) {
		this.products = products;
	}


   
}
