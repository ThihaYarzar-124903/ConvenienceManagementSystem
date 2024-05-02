package conveniencemanagementsystem.models;

import java.util.Date;
import java.util.List;

public class Sales {
    private int id;
  private int accounts_id;
    private Date purchase_date;
    private double total_amount;
    private String discount_percent;
    
   

  //private String qr_code;
  private List<SalesDetails> salesDetails;
    
  public Sales() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccounts_id() {
    return accounts_id;
  }

  public void setAccounts_id(int accounts_id) {
    this.accounts_id = accounts_id;
  }

  public Date getPurchase_date() {
    return purchase_date;
  }

  public void setPurchase_date(Date purchase_date) {
    this.purchase_date = purchase_date;
  }

  public double getTotal_amount() {
    return total_amount;
  }

  public void setTotal_amount(double total_amount) {
    this.total_amount = total_amount;
  }
  
    public List<SalesDetails> getSalesDetails() {
    return salesDetails;
  }

  public void setSalesDetails(List<SalesDetails> salesDetails) {
    this.salesDetails = salesDetails;
  }
   public String getDiscount_percent() {
      return discount_percent;
    }

    public void setDiscount_percent(String discount_percent) {
      this.discount_percent = discount_percent;
    }
  
  
//  public String getQr_code() {
//    return qr_code;
//  }
//
//  public void setQr_code(String qr_code) {
//    this.qr_code = qr_code;
//  }
}