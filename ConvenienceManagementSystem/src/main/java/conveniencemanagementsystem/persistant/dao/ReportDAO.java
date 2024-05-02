package conveniencemanagementsystem.persistant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.persistant.dto.ProductReportDTO;
import conveniencemanagementsystem.persistant.dto.ReportsDTO;

public class ReportDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/storedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection conn;

    public ReportDAO() throws SQLException {
        conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public List<ReportsDTO> getSalesByDateRangeAndAccountId(String fromDate, String toDate, int accountId) throws SQLException {
        List<ReportsDTO> salesList = new ArrayList<>();
        String sql = "SELECT s.id AS sales_id, a.username, s.purchase_date, s.total_amount " +
                     "FROM sales s " +
                     "INNER JOIN accounts a ON s.accounts_id = a.id " +
                     "WHERE s.purchase_date BETWEEN ? AND ? AND s.accounts_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fromDate);
            pstmt.setString(2, toDate);
            pstmt.setInt(3, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ReportsDTO sales = new ReportsDTO();
                    sales.setSalesId(rs.getInt("sales_id"));
                    sales.setUsername(rs.getString("username"));
                    sales.setPurchaseDate(rs.getString("purchase_date"));
                    sales.setTotalAmount(rs.getDouble("total_amount"));
                    salesList.add(sales);
                }
            }
        }
        return salesList;
    }

    public ReportsDTO getSaleDetailsById(int saleId) {
        ReportsDTO salesReport = new ReportsDTO();
        List<ProductReportDTO> products = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Check if the 'account' table exists
            if (!isAccountTableExists(connection)) {
                throw new IllegalStateException("Table 'accounts' does not exist in the database.");
            }

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

    private boolean isAccountTableExists(Connection connection) throws SQLException {
        return connection.getMetaData().getTables(null, null, "accounts", null).next();
    }
}
