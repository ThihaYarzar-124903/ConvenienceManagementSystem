package conveniencemanagementsystem.persistant.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import conveniencemanagementsystem.models.ProductBean;
import conveniencemanagementsystem.persistant.dto.ProductDTO;


public class ProductDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	//insert
	public int addProduct(ProductDTO productDTO) {
		int result=0;
		System.out.println(productDTO.getProducts_categories_id());
		String sql="INSERT INTO products(name,product_code,products_categories_id,original_price,sold_price,product_qty,product_image,product_detail) VALUES(?,?,?,?,?,?,?,?)";
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1, productDTO.getName());
			ps.setString(2, productDTO.getProduct_code());
			ps.setInt(3, productDTO.getProducts_categories_id());
			ps.setDouble(4, productDTO.getOriginal_price());
			ps.setDouble(5, productDTO.getSold_price());
			ps.setInt(6, productDTO.getProduct_qty());
			ps.setBlob(7, productDTO.getImage_blob());
			ps.setString(8, productDTO.getProduct_detail());
			
			result=ps.executeUpdate();
			
		}catch(SQLException e) {
			
			System.out.println("Insert error: "+e);
		}		
		return result;
	}
	
	//update
	public int editProduct(ProductDTO productDTO) {
		int result=0;
		System.out.println(productDTO.getProducts_categories_id());
		String sql="UPDATE products SET name=?,product_code=?,products_categories_id=?,original_price=?,sold_price=?,product_qty=?,product_image=?,product_detail=?" + "WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setString(1, productDTO.getName());
			ps.setString(2, productDTO.getProduct_code());
			ps.setInt(3, productDTO.getProducts_categories_id());
			ps.setDouble(4, productDTO.getOriginal_price());
			ps.setDouble(5, productDTO.getSold_price());
			ps.setInt(6, productDTO.getProduct_qty());
			ps.setBlob(7, productDTO.getImage_blob());
			ps.setString(8, productDTO.getProduct_detail());
			
			ps.setInt(9, productDTO.getId());
			
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			 System.out.println("Update error: " + e);
			    e.printStackTrace();
		}		
		return result;
	}
	
	//delete
	public int deleteProduct(int id) {
		int result=0;
		String sql="DELETE FROM products WHERE id=?";
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
	public ProductDTO getProductById(int id) {
	ProductDTO product=new ProductDTO();
		String sql="SELECT * FROM products WHERE id=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setProduct_code(rs.getString("product_code"));
				product.setProducts_categories_id(rs.getInt("products_categories_id"));				
				product.setOriginal_price(rs.getDouble("original_price"));
				product.setSold_price(rs.getDouble("sold_price"));
				product.setProduct_qty(rs.getInt("product_qty"));
				product.setProduct_image(rs.getString("product_image"));
				product.setProduct_detail(rs.getString("product_detail"));
				
			}				
		}catch(SQLException e) {
			System.out.println("select by code error"+e);
		}
		return product;
	}
	
	//selectById
	public ProductDTO geProductByName(String name) {
		ProductDTO product = new ProductDTO();
			String sql = "SELECT * FROM products WHERE name=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setProduct_code(rs.getString("product_code"));
					product.setOriginal_price(rs.getDouble("original_price"));
					product.setSold_price(rs.getDouble("sold_price"));
					product.setProduct_qty(rs.getInt("product_qty"));
					product.setProduct_image(rs.getString("product_image"));
					product.setProduct_detail(rs.getString("product_detail"));
				}
			} catch (SQLException e) {
				System.out.println("select author by id error" + e);
			}
			
			return product;
	}
	
	//selectNameById
	public String getProductNameById(int id) {
		String name="";
		String sql = "SELECT name FROM products WHERE id=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {					
				name=rs.getString("name");					
			}
		} catch (SQLException e) {
			
			System.out.println("select product by id error" + e);
		}
		
		return name;
	}
	
//	public String getProductCodeById(int id) {
//		String name="";
//		String sql = "SELECT product_code FROM products WHERE id=?";
//		try {
//			
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {					
//				name=rs.getString("product_code");					
//			}
//		} catch (SQLException e) {
//			
//			System.out.println("select product by id error" + e);
//		}
//		
//		return name;
//	}
//	
//	public Double getProductSoldPriceById(int id) {
//		Double soldPrice=0.0;
//		String sql = "SELECT sold_price FROM products WHERE id=?";
//		try {
//			
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {					
//				soldPrice=rs.getDouble("sold_price");					
//			}
//		} catch (SQLException e) {
//			
//			System.out.println("select product by id error" + e);
//		}
//		
//		return soldPrice;
//	}
	
	//selectAll
	public List<ProductDTO> getAllProducts() throws UnsupportedEncodingException {
		List<ProductDTO> products=new ArrayList<ProductDTO>();
		String sql="SELECT * FROM products";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);			
			ResultSet rs=ps.executeQuery();			
			while(rs.next()) {
				
				ProductDTO product=new ProductDTO();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setProduct_code(rs.getString("product_code"));
				product.setProducts_categories_id(rs.getInt("products_categories_id"));				
				product.setOriginal_price(rs.getDouble("original_price"));
				product.setSold_price(rs.getDouble("sold_price"));
				product.setProduct_qty(rs.getInt("product_qty"));
				
				Blob image_Blob = (Blob) rs.getBlob("product_image");
				byte[] bytes = image_Blob.getBytes(1, (int) image_Blob.length());
				byte[] encodeBase64 = Base64.encodeBase64(bytes);
				product.setProduct_image(new String(encodeBase64, "UTF-8"));
				
				product.setProduct_detail(rs.getString("product_detail"));
				
				products.add(product);
			}				
		}catch(SQLException e) {
			System.out.println("select all error: "+e);
		}
		return products;
	}
	
	public int getLastProductId() {
	    int lastProductId = 0;
	    String sql = "SELECT MAX(id) as productId FROM products";
	    
	    try {
	      
	      PreparedStatement ps=con.prepareStatement(sql);      
	      ResultSet rs=ps.executeQuery();  
	      
	      while(rs.next()) {
	        lastProductId = rs.getInt("productId"); 
	      }        
	    }catch(SQLException e) {
	      System.out.println("select Last Id error: "+e);
	    }
	    return lastProductId;
	}
	
	//selectById
	public List<ProductDTO> getProdcutsByDiscountId(int discounts_id) {
		List<ProductDTO> prodcuts = new ArrayList<ProductDTO>();			
			String sql = "SELECT p.* FROM products p INNER JOIN  discounts_has_products dp  ON  p.id = dp.products_id WHERE dp.discounts_id=?";;
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, discounts_id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					
					ProductDTO product = new ProductDTO();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setProduct_code(rs.getString("product_code"));
					product.setOriginal_price(rs.getDouble("original_price"));
					product.setSold_price(rs.getDouble("sold_price"));
					product.setProduct_qty(rs.getInt("product_qty"));
					product.setProduct_image(rs.getString("product_image"));
					product.setProduct_detail(rs.getString("product_detail"));
					
					prodcuts.add(product);
				}
			} catch (SQLException e) {
				System.out.println("select product by id error" + e);
		}
			
		return prodcuts;
	}
	
	public List<ProductDTO> getProductsByWithoutDiscount() throws UnsupportedEncodingException{
		List<ProductDTO> products=new ArrayList<ProductDTO>();
		String sql="SELECT p.* FROM products p "
				  + "WHERE p.id NOT IN (SELECT dp.products_id FROM discounts_has_products dp "
				  + "JOIN discounts d ON dp.discounts_id = d.id WHERE NOT d.to_date < NOW());";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);			
			ResultSet rs=ps.executeQuery();			
			while(rs.next()) {
				
				ProductDTO product=new ProductDTO();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setProduct_code(rs.getString("product_code"));
				product.setProducts_categories_id(rs.getInt("products_categories_id"));				
				product.setOriginal_price(rs.getDouble("original_price"));
				product.setSold_price(rs.getDouble("sold_price"));
				product.setProduct_qty(rs.getInt("product_qty"));
				
				Blob image_Blob = (Blob) rs.getBlob("product_image");
				byte[] bytes = image_Blob.getBytes(1, (int) image_Blob.length());
				byte[] encodeBase64 = Base64.encodeBase64(bytes);
				product.setProduct_image(new String(encodeBase64, "UTF-8"));
				
				product.setProduct_detail(rs.getString("product_detail"));
				
				products.add(product);
			}				
		}catch(SQLException e) {
			System.out.println("select all error: "+e);
		}
		return products;
	}
	
	public Integer getProductsCount() {
		try {
			Integer count = 0;
			PreparedStatement ps = con.prepareStatement("Select count(*) as product_count from products");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("product_count");
			}
			return count;
		}catch(SQLException e){
			System.out.print("Error at getProductsCount() "+e.getMessage());
		}
		return null;
	}

	public void updateProductQuantity(int productId, int newQuantity) {
	    String sql = "UPDATE products SET product_qty = product_qty+ ? WHERE id = ?";

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, newQuantity);
	        ps.setInt(2, productId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error updating product quantity: " + e.getMessage());
	    }
	}
	
	public List<ProductBean> getProductsByCategoryId(int categoryId) {
	     List<ProductBean> products = new ArrayList<>();
	     String sql = "SELECT * FROM products WHERE products_categories_id = ?";
	     try {
	          PreparedStatement ps = con.prepareStatement(sql);
	          ps.setInt(1, categoryId);
	          ResultSet rs = ps.executeQuery();
	          while (rs.next()) {
	              ProductBean product = new ProductBean();
	              product.setId(rs.getInt("id"));
	              product.setName(rs.getString("name"));
	              products.add(product);
	            }
	     } catch (SQLException e) {
	         System.out.println("Error fetching products by category ID: " + e.getMessage());
	     }
	     
	     return products;
	 }

	
}
