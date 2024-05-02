package conveniencemanagementsystem.persistant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.models.Sales;
import conveniencemanagementsystem.models.SlipReport;

public class SalesDAO {
	
    public Integer insertSales(Sales sales) {
        String insertSalesSql = "INSERT INTO sales (accounts_id, purchase_date, total_amount) VALUES (?, ?, ?)";

        try (Connection con = MyConnection.getConnection();
             PreparedStatement insertSalesPs = con.prepareStatement(insertSalesSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertSalesPs.setInt(1, sales.getAccounts_id());
            java.sql.Date sqlDate = new java.sql.Date(sales.getPurchase_date().getTime());
            insertSalesPs.setDate(2, sqlDate);
            insertSalesPs.setDouble(3, sales.getTotal_amount());

            int affectedRows = insertSalesPs.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = insertSalesPs.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int salesId = generatedKeys.getInt(1);

                        for (var detail : sales.getSalesDetails()) {
                            insertSalesDetails(con, salesId, detail.getProducts_id(), detail.getQty(), detail.getDiscounts_id());
                            updateProductQty(con, detail.getProducts_id(), detail.getQty());
                        }
                        return salesId;
                    }
                }
            }

        } catch (SQLException e) {
            // Handle the exception based on your application's requirements.
            e.printStackTrace();
        }
        return null;
    }

    private void insertSalesDetails(Connection con, int salesId, int productsId, int qty, int discountsId) throws SQLException {
        DiscountDAO discountsDAO = new DiscountDAO();

        boolean isDiscountExpired = discountsDAO.isDiscountExpired(discountsId);
        String insertSlipDetailsSql;
        if (isDiscountExpired || discountsId == 0) {
            insertSlipDetailsSql = "INSERT INTO sales_details (sales_id, products_id, qty) VALUES (?, ?, ?)";
        } else {
            insertSlipDetailsSql = "INSERT INTO sales_details (sales_id, products_id, qty, discounts_id) VALUES (?, ?, ?, ?)";
        }

        try (PreparedStatement insertSlipDetailsPs = con.prepareStatement(insertSlipDetailsSql)) {
            insertSlipDetailsPs.setInt(1, salesId);
            insertSlipDetailsPs.setInt(2, productsId);
            insertSlipDetailsPs.setInt(3, qty);
            if (!isDiscountExpired && discountsId != 0) {
                insertSlipDetailsPs.setInt(4, discountsId);
            }

            insertSlipDetailsPs.executeUpdate();

        } catch (SQLException e) {
            // Handle the exception based on your application's requirements.
            e.printStackTrace();
        }
    }


    private void updateProductQty(Connection con, int productId, int quantity) {
        String updateProductQtySql = "UPDATE products SET product_qty = product_qty - ? WHERE id = ?";

        try (PreparedStatement updateProductQtyPs = con.prepareStatement(updateProductQtySql)) {
            updateProductQtyPs.setInt(1, quantity);
            updateProductQtyPs.setInt(2, productId);
            updateProductQtyPs.executeUpdate();
        } catch (SQLException e) {
            // Handle the exception based on your application's requirements.
            e.printStackTrace();
        }
    }

    public List<SlipReport> getPrintData(int saleId) {
        List<SlipReport> slips = new ArrayList<>();
        String selectSlipReportSql =  
                "SELECT s.*, a.username, sd.discounts_id, sd.products_id, p.name AS products_name, " +
                "p.product_code, p.sold_price, sd.qty, COALESCE(d.discount_percent, 0) AS discount_percent " +
                "FROM sales s " +
                "INNER JOIN sales_details sd ON s.id = sd.sales_id " +
                "INNER JOIN products p ON sd.products_id = p.id " +
                "INNER JOIN accounts a ON a.id = s.accounts_id " +
                "LEFT JOIN discounts d ON sd.discounts_id = d.id " +
                "WHERE s.id = ?";
        
        try (Connection con = MyConnection.getConnection();
             PreparedStatement selectSlipReportPs = con.prepareStatement(selectSlipReportSql)) {

            selectSlipReportPs.setInt(1, saleId);
            ResultSet rs = selectSlipReportPs.executeQuery();

            while (rs.next()) {
                SlipReport slip = new SlipReport();
                slip.setId(rs.getInt("id"));
                slip.setAccounts_id(rs.getInt("accounts_id"));
                slip.setUsername(rs.getString("username"));
                slip.setPurchase_date(rs.getTimestamp("purchase_date"));
                slip.setTotal_amount(rs.getBigDecimal("total_amount"));
                slip.setProducts_id(rs.getInt("products_id"));
                slip.setProduct_code(rs.getString("product_code"));
                slip.setName(rs.getString("products_name"));
                slip.setSold_price(rs.getBigDecimal("sold_price"));
                slip.setQty(rs.getInt("qty"));
                slip.setDiscounts_id(rs.getInt("discounts_id"));
                slip.setDiscount_percent(rs.getInt("discount_percent"));
              
                slips.add(slip);
            }

        } catch (SQLException e) {
            // Handle the exception based on your application's requirements.
            e.printStackTrace();
        }

        return slips;
    }

//    public Integer insertSales(Sales sales) {
//        String insertSalesSql = "INSERT INTO sales (accounts_id, purchase_date, total_amount) VALUES (?, ?, ?)";
//
//        try (Connection con = MyConnection.getConnection();
//             PreparedStatement insertSalesPs = con.prepareStatement(insertSalesSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//
//            insertSalesPs.setInt(1, sales.getAccounts_id());
//            java.sql.Date sqlDate = new java.sql.Date(sales.getPurchase_date().getTime());
//            insertSalesPs.setDate(2, sqlDate);
//            insertSalesPs.setDouble(3, sales.getTotal_amount());
//
//            int affectedRows = insertSalesPs.executeUpdate();
//
//            if (affectedRows > 0) {
//                try (ResultSet generatedKeys = insertSalesPs.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        int salesId = generatedKeys.getInt(1);
//
//                        for (var detail : sales.getSalesDetails()) {
//                            String salesDetailsSql = (detail.getDiscounts_id() == 0) ?
//                                    "INSERT INTO sales_details (sales_id, products_id, qty) VALUES (?, ?, ?)" :
//                                    "INSERT INTO sales_details (sales_id, products_id, qty, discounts_id) VALUES (?, ?, ?, ?)";
//
//                            try (PreparedStatement salesDetailsPs = con.prepareStatement(salesDetailsSql)) {
//                                salesDetailsPs.setInt(1, salesId);
//                                salesDetailsPs.setInt(2, detail.getProducts_id());
//                                salesDetailsPs.setInt(3, detail.getQty());
//
//                                if (detail.getDiscounts_id() != 0) {
//                                    salesDetailsPs.setInt(4, detail.getDiscounts_id());
//                                }
//
//                                salesDetailsPs.executeUpdate();
//                            }
//
//                            updateProductQty(con, detail.getProducts_id(), detail.getQty());
//                        }
//                        return salesId;
//                    }
//                }
//            }
//
//        } catch (SQLException e) {
//            // Handle the exception based on your application's requirements.
//            e.printStackTrace();
//        }
//		return null;
//    }
//
//    private void updateProductQty(Connection con, int productId, int quantity) {
//        String updateProductQtySql = "UPDATE products SET product_qty = product_qty - ? WHERE id = ?";
//
//        try (PreparedStatement updateProductQtyPs = con.prepareStatement(updateProductQtySql)) {
//            updateProductQtyPs.setInt(1, quantity);
//            updateProductQtyPs.setInt(2, productId);
//            updateProductQtyPs.executeUpdate();
//        } catch (SQLException e) {
//            // Handle the exception based on your application's requirements.
//            e.printStackTrace();
//        }
//    }
//
//    public List<SlipReport> getPrintData(int saleId) {
//        List<SlipReport> slips = new ArrayList<>();
//        String selectSlipReportSql =  
//                "SELECT s.*, a.username, sd.discounts_id, sd.products_id, p.name AS products_name, " +
//                "p.product_code, p.sold_price, sd.qty, COALESCE(d.discount_percent, 0) AS discount_percent " +
//                "FROM sales s " +
//                "INNER JOIN sales_details sd ON s.id = sd.sales_id " +
//                "INNER JOIN products p ON sd.products_id = p.id " +
//                "INNER JOIN accounts a ON a.id = s.accounts_id " +
//                "LEFT JOIN discounts d ON sd.discounts_id = d.id " +
//                "WHERE s.id = ?";
//        
//        try (Connection con = MyConnection.getConnection();
//             PreparedStatement selectSlipReportPs = con.prepareStatement(selectSlipReportSql)) {
//
//            selectSlipReportPs.setInt(1, saleId);
//            ResultSet rs = selectSlipReportPs.executeQuery();
//
//            while (rs.next()) {
//                SlipReport slip = new SlipReport();
//                slip.setId(rs.getInt("id"));
//                slip.setAccounts_id(rs.getInt("accounts_id"));
//                slip.setPurchase_date(rs.getTimestamp("purchase_date"));
//                slip.setTotal_amount(rs.getBigDecimal("total_amount"));
//                slip.setProducts_id(rs.getInt("products_id"));
//                slip.setProduct_code(rs.getString("product_code"));
//                slip.setName(rs.getString("products_name"));
//                slip.setSold_price(rs.getBigDecimal("sold_price"));
//                slip.setQty(rs.getInt("qty"));
//                slip.setDiscounts_id(rs.getInt("discounts_id"));
//                slip.setDiscount_percent(rs.getInt("discount_percent"));
//              
//                slips.add(slip);
//            }
//
//        } catch (SQLException e) {
//            // Handle the exception based on your application's requirements.
//            e.printStackTrace();
//        }
//
//        return slips;
//    }
}
