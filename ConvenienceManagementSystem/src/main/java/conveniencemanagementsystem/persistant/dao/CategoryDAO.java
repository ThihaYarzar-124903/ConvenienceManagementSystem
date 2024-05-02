package conveniencemanagementsystem.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.persistant.dto.CategoryRequestDTO;
import conveniencemanagementsystem.persistant.dto.CategoryResponseDTO;


public class CategoryDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public int addCategory(CategoryRequestDTO categoryDTO) {
		int result=0;
		String sql="INSERT INTO products_categories(name) VALUES(?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, categoryDTO.getName());
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Insert error: "+e);
		}		
		return result;
	}
	
	public int editCategory(CategoryRequestDTO categoryDTO) {
		int result=0;
		String sql="UPDATE products_categories SET name=?  WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);	
			ps.setString(1, categoryDTO.getName());
			ps.setInt(2, categoryDTO.getId());
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Update error: "+e);
		}		
		return result;
	}
	
	public int deleteCategory(int id) {
		int result=0;
		String sql="DELETE FROM products_categories WHERE id=?";
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Delete error: "+e);
		}		
		return result;
	}
	
	public CategoryResponseDTO getCategoryById(int id) {
		CategoryResponseDTO  category = new CategoryResponseDTO();
		String sql="SELECT * FROM  products_categories WHERE id=?";
			
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1,id);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("name"));
					
				}				
			}catch(SQLException e) {
				System.out.println("select by id error"+e);
			}
			
			return  category;
	}
	
	public String getCategoryNameById(int id) {
		String name="";
		String sql = "SELECT name FROM products_categories WHERE id=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {					
				name=rs.getString("name");					
			}
		} catch (SQLException e) {
			System.out.println("select author by id error" + e);
		}
		
		return name;
	}
		
	public List<CategoryResponseDTO> getAllCategory() {
		List<CategoryResponseDTO> categorys = new ArrayList<CategoryResponseDTO>();
		String sql="SELECT * FROM products_categories";
			
			try {
				
				PreparedStatement ps=con.prepareStatement(sql);			
				ResultSet rs=ps.executeQuery();			
				while(rs.next()) {
					CategoryResponseDTO  category=new CategoryResponseDTO();
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("name"));
					categorys.add(category);
				}				
			}catch(SQLException e) {
				System.out.println("select all error: "+e);
			}
			
			return  categorys;
	}
	
	public Integer getCategoriesCount() {
		try {
			Integer count = 0;
			PreparedStatement ps = con.prepareStatement("Select count(*) as category_count from products_categories");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("category_count");
			}
			return count;
		}catch(SQLException e){
			System.out.print("Error at getCategoriesCount() "+e.getMessage());
		}
		return null;
	}
	
//	public List<CategoryBean> getAllCategories() {
//	    List<CategoryBean> list = new ArrayList<>();
//	    try {
//	        String query = "SELECT pc.id AS category_id, pc.name AS category_name, " +
//	                       "COUNT(p.id) AS product_count " +
//	                       "FROM products_categories pc " +
//	                       "INNER JOIN products p ON pc.id = p.products_categories_id " +
//	                       "GROUP BY pc.id, pc.name";
//	        PreparedStatement ps = con.prepareStatement(query);
//	        ResultSet rs = ps.executeQuery();
//	        while (rs.next()) {
//	            CategoryBean category = new CategoryBean();
//	            category.setId(rs.getInt("category_id"));
//	            category.setName(rs.getString("category_name"));
//	            category.setProductCount(rs.getInt("product_count"));
//	            list.add(category);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return list;
//	}
	
	
}
