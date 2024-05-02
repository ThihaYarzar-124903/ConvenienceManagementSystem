package conveniencemanagementsystem.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conveniencemanagementsystem.persistant.dto.DiscountDTO;
import conveniencemanagementsystem.persistant.dto.ProductDTO;


public class DiscountDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}

	//insert
	public int addDiscount(DiscountDTO discountDTO) {
		int result=0;		
		String sql="INSERT INTO discounts(name,discount_percent,from_date,to_date) VALUES(?,?,?,?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, discountDTO.getName());
			ps.setInt(2, discountDTO.getDiscount_percent());			
			ps.setString(3, discountDTO.getFrom_date());
			ps.setString(4, discountDTO.getTo_date());
			result=ps.executeUpdate();
			
			if(result!=0) {
				for(ProductDTO product : discountDTO.getProducts()) {
					sql="INSERT INTO discounts_has_products(discounts_id,products_id) VALUES(last_insert_id(),?)";
					ps=con.prepareStatement(sql);
					
					//System.out.println("product Id"+product.getId());
					
					ps.setInt(1, product.getId());
					result=ps.executeUpdate();
				}				
			}
		}catch(SQLException e) {
			System.out.println("Insert error: "+e);
		}		
		return result;
	}
	
	//update
	public int editDiscount(DiscountDTO discountDTO) {
		int result=0;
		
		String sql="UPDATE discounts SET name=?,discount_percent=?,from_date=?,to_date=? WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, discountDTO.getName());			
			ps.setInt(2, discountDTO.getDiscount_percent());			
			ps.setString(3, discountDTO.getFrom_date());
			ps.setString(4, discountDTO.getTo_date());
			ps.setInt(5, discountDTO.getId());
			
			result=ps.executeUpdate();
			
			sql="DELETE FROM discounts_has_products WHERE discounts_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, discountDTO.getId());
			result=ps.executeUpdate();
			
			
			for(ProductDTO product:discountDTO.getProducts()) {
				sql="INSERT INTO discounts_has_products(discounts_id,products_id) VALUES(?,?)";
				ps=con.prepareStatement(sql);
				ps.setInt(1, discountDTO.getId());
				ps.setInt(2, product.getId());
				result=ps.executeUpdate();
				
			}						
		}catch(SQLException e) {
			System.out.println("Update error: "+e);
		}		
		return result;
	}
	
	//delete
	public int deleteDiscount(int id) {
		int result=0;
		String sql="DELETE FROM discounts WHERE id=?";
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Delete error: "+e);
		}		
		return result;
	}

	//selectById
	public DiscountDTO getDiscountById(int id) {
		DiscountDTO discount=new DiscountDTO();
		String sql="SELECT * FROM discounts WHERE id=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				discount.setId(rs.getInt("id"));
				discount.setName(rs.getString("name"));
				discount.setDiscount_percent(rs.getInt("discount_percent"));				
				discount.setFrom_date(rs.getString("from_date"));
				discount.setTo_date(rs.getString("to_date"));
				
			}				
		}catch(SQLException e) {
			System.out.println("select by code error"+e);
		}
		return discount;
	}
	
	public int getDiscountPercentById(int id) {
		int percent=0;
		String sql = "SELECT discount_percent FROM discounts WHERE id=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {					
				percent=rs.getInt("discount_percent");					
			}
		} catch (SQLException e) {
			
			System.out.println("select discount by id error" + e);
		}
		
		return percent;
	}
	
	public DiscountDTO getDiscountWithLazyById(int id) {
		DiscountDTO discount = new DiscountDTO();
		String sql="SELECT * FROM discounts WHERE id=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				discount.setId(rs.getInt("id"));
				discount.setName(rs.getString("name"));								
				discount.setDiscount_percent(rs.getInt("discount_percent"));
				discount.setFrom_date(rs.getString("from_date"));
				discount.setTo_date(rs.getString("to_date"));
				
				sql="SELECT p.* FROM discounts_has_products dp inner join products p on dp.products_id = p.id WHERE dp.discounts_id=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet join_rs=ps.executeQuery();
				
				List<ProductDTO> products=new ArrayList<ProductDTO>();
				while(join_rs.next()) {
					ProductDTO product = new ProductDTO();
					
					product.setId(join_rs.getInt("id"));
					product.setName(join_rs.getString("name"));
					product.setProduct_code(join_rs.getString("product_code"));
					product.setOriginal_price(join_rs.getDouble("original_price"));
					product.setOriginal_price(join_rs.getDouble("original_price"));
					product.setSold_price(join_rs.getDouble("sold_price"));
					product.setProduct_image(join_rs.getString("product_image"));
					product.setProduct_detail(join_rs.getString("product_detail"));
					
					products.add(product);				
				}				
				discount.setProducts(products);				
			}				
		}catch(SQLException e) {
			System.out.println("select by id error"+e);
		}
		return discount;
	}
	
	//selectAll
	public List<DiscountDTO> getAllDiscounts() {
		List<DiscountDTO> discounts=new ArrayList<DiscountDTO>();
		String sql="SELECT * FROM discounts";
		ProductDAO productDAO=new ProductDAO();
		try {
			PreparedStatement ps=con.prepareStatement(sql);			
			ResultSet rs=ps.executeQuery();			
			while(rs.next()) {
				DiscountDTO discount=new DiscountDTO();
				discount.setId(rs.getInt("id"));
				discount.setName(rs.getString("name"));			
				discount.setDiscount_percent(rs.getInt("discount_percent"));
				discount.setFrom_date(rs.getString("from_date"));
				discount.setTo_date(rs.getString("to_date"));
				
				discount.setProducts(productDAO.getProdcutsByDiscountId(rs.getInt("id")));	
				
				discounts.add(discount);
			}				
		}catch(SQLException e) {
			System.out.println("select all error: "+e);
		}
		return discounts;
	}

    public boolean isDiscountExpired(int discountsId) {
        String sql = "SELECT to_date FROM discounts WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, discountsId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date toDate = rs.getDate("to_date");
                LocalDate expiryDate = ((java.sql.Date) toDate).toLocalDate();
                LocalDate currentDate = LocalDate.now();
                return currentDate.isAfter(expiryDate);
            }
        } catch (SQLException e) {
            System.out.println("Error checking discount expiry: " + e);
        }
        
        return false;
    }
}
