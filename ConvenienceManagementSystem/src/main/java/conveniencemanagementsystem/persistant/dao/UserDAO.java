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

import conveniencemanagementsystem.persistant.dto.UserRequestDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

public class UserDAO {
  public static Connection con=null;
  static {
    con=MyConnection.getConnection();
  }
  
  //insert
  public int addUser(UserRequestDTO userDTO) {
    int result=0;
    String sql="INSERT INTO accounts(username,email,staff_id,roles_id,dob,gender,phone,nrc,profile_image,password) "
        + "VALUES(?,?,?,?,?,?,?,?,?,?)";
    try {
      PreparedStatement ps=con.prepareStatement(sql);
      ps.setString(1, userDTO.getUsername());
      ps.setString(2, userDTO.getEmail());
      ps.setString(3, userDTO.getStaff_id());
      ps.setInt(4, 2);
      ps.setString(5, userDTO.getDob());
      ps.setString(6, userDTO.getGender());
      ps.setString(7, userDTO.getPhone());
      ps.setString(8, userDTO.getNrc());
      ps.setBlob(9, userDTO.getImage_blob());
      ps.setString(10, userDTO.getPassword());
      
      result=ps.executeUpdate();      
    }catch(SQLException e) {
      System.out.println("Insert error: "+e);
    }
    
    return result;
  }
  
  //update
  public int editUser(UserRequestDTO userDTO) {
    int result=0;
    
    String sql="UPDATE accounts SET username=?,email=?, staff_id=?, dob=?, gender=?, phone=?, nrc=?, profile_image=?, password=?"
        + " WHERE id=?";
    try {
      PreparedStatement ps=con.prepareStatement(sql);
      ps.setString(1, userDTO.getUsername());
      ps.setString(2, userDTO.getEmail());
      ps.setString(3, userDTO.getStaff_id());
      ps.setString(4, userDTO.getDob());
      ps.setString(5, userDTO.getGender());
      ps.setString(6, userDTO.getPhone());
      ps.setString(7, userDTO.getNrc());
      ps.setBlob(8, userDTO.getImage_blob());
      ps.setString(9, userDTO.getPassword());
      
      ps.setInt(10, userDTO.getId());
      
      result=ps.executeUpdate();      
    }catch(SQLException e) {
      System.out.println("Insert error: "+e);
    }    
    return result;
  }
  
  //delete
  public int deleteUser(int id) {
    int result=0;
    String sql="DELETE FROM accounts WHERE id=?";
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
  public UserResponseDTO getUserById(int id) {
    UserResponseDTO  user=new UserResponseDTO();
    String sql="SELECT * FROM  accounts WHERE id=?";
      
    try {
      PreparedStatement ps=con.prepareStatement(sql);
      ps.setInt(1,id);
      ResultSet rs=ps.executeQuery();
        
      while(rs.next()) {
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setRoles_id(rs.getInt("roles_id"));  
        user.setStaff_id(rs.getString("staff_id"));
        user.setDob(rs.getString("dob"));
        user.setGender(rs.getString("gender"));
        user.setPhone(rs.getString("phone"));
        user.setNrc(rs.getString("nrc"));
        user.setPassword(rs.getString("password"));
          
      }
      
    }catch(SQLException e) {
      System.out.println("select by id error"+e);
    }
    
    return  user;
  }
  
  //selectAll
  public static List<UserResponseDTO> getAllUser() throws UnsupportedEncodingException {
    List<UserResponseDTO> users=new ArrayList<UserResponseDTO>();
    String sql="SELECT * FROM accounts";
      
    try {
      PreparedStatement ps=con.prepareStatement(sql);      
      ResultSet rs=ps.executeQuery();      
      while(rs.next()) {
        
        UserResponseDTO  user=new UserResponseDTO();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setRoles_id(rs.getInt("roles_id"));
        user.setStaff_id(rs.getString("staff_id"));
        user.setDob(rs.getString("dob"));
        user.setGender(rs.getString("gender"));
        user.setPhone(rs.getString("phone"));
        user.setNrc(rs.getString("nrc"));
        Blob image_Blob = (Blob) rs.getBlob("profile_image");
        byte[] bytes = image_Blob.getBytes(1, (int) image_Blob.length());
        byte[] encodeBase64 = Base64.encodeBase64(bytes);
        user.setProfile_image(new String(encodeBase64, "UTF-8"));
        user.setPassword(rs.getString("password"));
        user.setIs_disabled(rs.getBoolean("is_disabled"));

        users.add(user);
      }        
    }catch(SQLException e) {
      System.out.println("select all error: "+e);
    }
    
    return  users;
  }
    
  public int disableUser(int userId) {
    int result=0;
    String sql="UPDATE accounts SET is_disabled=?" + " WHERE id=?";
    try {
      
      PreparedStatement ps=con.prepareStatement(sql);  
      ps.setBoolean(1, false);
      ps.setInt(2, userId);

      result=ps.executeUpdate();
      
    }catch(SQLException e) {
      System.out.println("Update error: "+e);
    }
    
    return result;
    
  }
    
  public int enableUser(int userId) {
    int result=0;
    String sql="UPDATE accounts SET is_disabled=?" + " WHERE id=?";
    try {
      PreparedStatement ps=con.prepareStatement(sql);  
      ps.setBoolean(1, true);
      ps.setInt(2, userId);

      result=ps.executeUpdate();  
      
    }catch(SQLException e) {
      System.out.println("Update error: "+e);
    }  
    
    return result;
    
  }
    
  public static UserResponseDTO selectAllUser(UserRequestDTO dto) {
    UserResponseDTO user = new UserResponseDTO();
      
    String sql = "select password,roles_id from accounts where email=? ";
      try {
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dto.getEmail());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          user.setPassword(rs.getString("password"));
          user.setRoles_id(rs.getInt("roles_id"));
          
          
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
      
    public int getLastStaffId() {
       int lastStaffId = 0;
       String sql = "SELECT MAX(id) as staffId FROM accounts";
          
        try {
            
           PreparedStatement ps=con.prepareStatement(sql);      
           ResultSet rs=ps.executeQuery();  
            
            while(rs.next()) {
              lastStaffId = rs.getInt("staffId"); 
            } 
            
        }catch(SQLException e) {
           System.out.println("select Last Id error: "+e);
        }
          return lastStaffId;
    }

    public static UserResponseDTO getUserByEmail(String email) {
        UserResponseDTO user = new UserResponseDTO();
        String sql = "SELECT * FROM accounts WHERE email=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRoles_id(rs.getInt("roles_id"));
                user.setStaff_id(rs.getString("staff_id"));
                user.setDob(rs.getString("dob"));
                user.setGender(rs.getString("gender"));
                user.setPhone(rs.getString("phone"));
                user.setNrc(rs.getString("nrc"));
                // Add more fields as needed
                user.setPassword(rs.getString("password"));
                user.setIs_disabled(rs.getBoolean("is_disabled"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
  }