package conveniencemanagementsystem.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import conveniencemanagementsystem.models.ProductDetails;

public class ProductDetailsDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public ProductDetails getProductDetailsByProductCode(String productCode) {
        ProductDetails details = new ProductDetails();
        String sql = "SELECT p.id, p.name, p.sold_price, p.product_qty, (CASE WHEN d.to_date >= ? THEN d.discount_percent END) AS product_discount,d.id as discount_id  FROM products p LEFT JOIN discounts_has_products dp ON p.id = dp.products_id LEFT JOIN discounts d ON dp.discounts_id = d.id WHERE p.product_code = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, LocalDate.now().toString());
            ps.setString(2, productCode);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	
            	details.setId(rs.getInt("id"));
                details.setName(rs.getString("name"));
                details.setSold_price(rs.getDouble("sold_price"));
                details.setProduct_qty(rs.getInt("product_qty"));
                details.setProduct_discount(rs.getInt("product_discount"));
                details.setDiscount_id(rs.getInt("discount_id"));
               
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving product details: " + e);
        }

        return details;
    }
}

