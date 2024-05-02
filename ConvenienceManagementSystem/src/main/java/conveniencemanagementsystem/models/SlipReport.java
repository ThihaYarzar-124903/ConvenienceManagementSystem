package conveniencemanagementsystem.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SlipReport {
  private int id;
  
  
  private int accounts_id;
  private Timestamp purchase_date;
  private BigDecimal total_amount;
  private String qr_code;
  
  private String username;
  private int products_id;
  private String name;
  private String product_code;
  private BigDecimal sold_price;
  private int discount_percent;
  
  private int qty;
  
  private int discounts_id;
  public SlipReport() {
    
  }
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
  public Timestamp getPurchase_date() {
    return purchase_date;
  }
  public void setPurchase_date(Timestamp purchase_date) {
    this.purchase_date = purchase_date;
  }
  public BigDecimal getTotal_amount() {
    return total_amount;
  }
  public void setTotal_amount(BigDecimal total_amount) {
    this.total_amount = total_amount;
  }
  public String getQr_code() {
    return qr_code;
  }
  public void setQr_code(String qr_code) {
    this.qr_code = qr_code;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public int getProducts_id() {
    return products_id;
  }
  public void setProducts_id(int products_id) {
    this.products_id = products_id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getProduct_code() {
    return product_code;
  }
  public void setProduct_code(String product_code) {
    this.product_code = product_code;
  }
  public BigDecimal getSold_price() {
    return sold_price;
  }
  public void setSold_price(BigDecimal sold_price) {
    this.sold_price = sold_price;
  }
  public int getQty() {
    return qty;
  }
  public void setQty(int qty) {
    this.qty = qty;
  }
  public int getDiscounts_id() {
    return discounts_id;
  }
  public void setDiscounts_id(int discounts_id) {
    this.discounts_id = discounts_id;
  }
  public int getDiscount_percent() {
    return discount_percent;
  }
  public void setDiscount_percent(int discount_percent) {
    this.discount_percent = discount_percent;
  }
  

  
}