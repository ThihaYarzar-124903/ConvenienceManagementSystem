package conveniencemanagementsystem.persistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.persistant.dto.ProductReportDTO;
import conveniencemanagementsystem.persistant.dto.SalesReportDTO;

public class SalesDetailsDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/storedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

//    public List<SalesReportDTO> getSalesDetailsByAccountId(HttpServletRequest request) {
//        List<SalesReportDTO> salesDetailsList = new ArrayList<>();
//        HttpSession session = request.getSession();
//        Integer accountId = (Integer) session.getAttribute("accountId");
//        if (accountId == null) {

//            return salesDetailsList;
//        }
//
//        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//            String sql = "SELECT s.id AS sales_id, s.accounts_id, a.username, s.purchase_date, s.total_amount, sd.id AS sales_details_id, sd.products_id, p.name AS product_name, sd.discounts_id, d.discount_percent, sd.qty FROM sales AS s INNER JOIN accounts AS a ON s.accounts_id = a.id INNER JOIN sales_details AS sd ON s.id = sd.sales_id INNER JOIN products AS p ON sd.products_id = p.id LEFT JOIN discounts AS d ON sd.discounts_id = d.id WHERE DATE(s.purchase_date) = CURDATE()";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        SalesReportDTO report = new SalesReportDTO();
//                        report.setSaleId(resultSet.getInt("sales_id"));
//                        report.setUsername(resultSet.getString("username"));
//                        report.setPurchaseDate(resultSet.getDate("purchase_date"));
//                        report.setTotalAmount(resultSet.getDouble("total_amount"));
//                        report.setSalesDetailsId(resultSet.getInt("sales_details_id"));
//                        report.setProductName(resultSet.getString("product_name"));
//                        report.setDiscountPercent(resultSet.getInt("discount_percent"));
//                        report.setQty(resultSet.getInt("qty"));
//                        salesDetailsList.add(report);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            // Handle the exception as needed
//            e.printStackTrace();
//        }
//
//        return salesDetailsList;
//    }
      
//	public List<SalesReportDTO> getSalesDetailsByAccountId(int accountId, HttpServletRequest request) {
//        List<SalesReportDTO> salesDetailsList = new ArrayList<>();
//        
//		HttpSession session = request.getSession();
//        Integer user = (Integer) session.getAttribute("accountId");
//        if (user == null) {
//            return salesDetailsList;
//        }
//
//        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//            String sql = "SELECT s.id AS sales_id, s.accounts_id, a.username, s.purchase_date, s.total_amount, sd.id AS sales_details_id, sd.products_id, p.name AS product_name, sd.discounts_id, d.discount_percent, sd.qty FROM sales AS s INNER JOIN accounts AS a ON s.accounts_id = a.id INNER JOIN sales_details AS sd ON s.id = sd.sales_id INNER JOIN products AS p ON sd.products_id = p.id LEFT JOIN discounts AS d ON sd.discounts_id = d.id WHERE DATE(s.purchase_date) = CURDATE()";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        SalesReportDTO report = new SalesReportDTO();
//                        report.setSaleId(resultSet.getInt("sales_id"));
//                        report.setUsername(resultSet.getString("username"));
//                        report.setPurchaseDate(resultSet.getDate("purchase_date"));
//                        report.setTotalAmount(resultSet.getDouble("total_amount"));
//                        report.setSalesDetailsId(resultSet.getInt("sales_details_id"));
//                        report.setProductName(resultSet.getString("product_name"));
//                        report.setDiscountPercent(resultSet.getInt("discount_percent"));
//                        report.setQty(resultSet.getInt("qty"));
//                        salesDetailsList.add(report);
//                        
//                        System.out.println(salesDetailsList.getClass());
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            // Handle the exception as needed
//            e.printStackTrace();
//        }
//
//        return salesDetailsList;
//    }
	
    public List<SalesReportDTO> getSalesDetailsByAccountId(int accountId) {
        List<SalesReportDTO> salesDetailsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT s.id AS sales_id, s.accounts_id, a.username, s.purchase_date, s.total_amount, sd.id AS sales_details_id, sd.products_id, p.name AS product_name, sd.discounts_id, d.discount_percent, sd.qty FROM sales AS s INNER JOIN accounts AS a ON s.accounts_id = a.id INNER JOIN sales_details AS sd ON s.id = sd.sales_id INNER JOIN products AS p ON sd.products_id = p.id LEFT JOIN discounts AS d ON sd.discounts_id = d.id WHERE s.accounts_id = ? AND DATE(s.purchase_date) = CURDATE() GROUP BY s.id";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, accountId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        SalesReportDTO report = new SalesReportDTO();
                        report.setSaleId(resultSet.getInt("sales_id"));
                        report.setUsername(resultSet.getString("username"));
                        report.setPurchaseDate(resultSet.getDate("purchase_date"));
                        report.setTotalAmount(resultSet.getDouble("total_amount"));
                        report.setSalesDetailsId(resultSet.getInt("sales_details_id"));
                        //report.setProducts(getProductsForSale(resultSet.getInt("sales_id")));
                        salesDetailsList.add(report);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesDetailsList;
    }

//    public List<ProductReportDTO> getProductsForSale(int saleId) {
//        List<ProductReportDTO> products = new ArrayList<>();
//        
//        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//            String sql = "SELECT p.name AS product_name, d.discount_percent, sd.qty " +
//                    	"FROM sales_details sd " +
//                    	"JOIN products p ON sd.products_id = p.id " +
//                    	"LEFT JOIN discounts d ON sd.discounts_id = d.id " +
//                    	"WHERE sd.sales_id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, saleId);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        ProductReportDTO product = new ProductReportDTO();
//                        product.setProductName(resultSet.getString("product_name"));
//                        product.setDiscountPercent(resultSet.getInt("discount_percent"));
//                        product.setQty(resultSet.getInt("qty"));
//                        products.add(product);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return products;
//    }


	public SalesReportDTO getSaleDetailsById(int saleId) {
        SalesReportDTO salesReport = new SalesReportDTO();
        List<ProductReportDTO> products = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT p.name AS product_name, d.discount_percent, sd.qty " +
                    	 "FROM sales_details sd " +
                    	 "JOIN products p ON sd.products_id = p.id " +
                    	 "LEFT JOIN discounts d ON sd.discounts_id = d.id " +
                    	 "WHERE sd.sales_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, saleId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ProductReportDTO product = new ProductReportDTO();
                        product.setProductName(resultSet.getString("product_name"));
                        product.setDiscountPercent(resultSet.getInt("discount_percent"));
                        product.setQty(resultSet.getInt("qty"));
                        products.add(product);
                    }
                }
            }
            
            salesReport.setProducts(products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return salesReport;
    }
	
}