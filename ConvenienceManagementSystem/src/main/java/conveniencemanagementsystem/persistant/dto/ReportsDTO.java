package conveniencemanagementsystem.persistant.dto;


import java.util.List;

public class ReportsDTO {
    private int saleId;
    private int accountId;
    private String username;
    private String purchaseDate;

	private double totalAmount;
    private int salesDetailsId;


	private List<ProductReportDTO> products;
    
    public ReportsDTO() {}
    
	public int getSaleId() {
		return saleId;
	}
	public void setSalesId(int saleId) {
		this.saleId = saleId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ProductReportDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductReportDTO> products) {
		this.products = products;
	}
	
    public int getSalesDetailsId() {
		return salesDetailsId;
	}

	public void setSalesDetailsId(int salesDetailsId) {
		this.salesDetailsId = salesDetailsId;
	}
	
    public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

    
}
