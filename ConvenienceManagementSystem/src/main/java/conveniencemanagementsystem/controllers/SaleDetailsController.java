package conveniencemanagementsystem.controllers;


import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import conveniencemanagementsystem.models.ProductDetails;
import conveniencemanagementsystem.models.Sales;
import conveniencemanagementsystem.models.SlipReport;
import conveniencemanagementsystem.persistant.dao.ProductDetailsDAO;
import conveniencemanagementsystem.persistant.dao.SalesDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.*;

import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

@SuppressWarnings({ "unused", "deprecation" })
@Controller
public class SaleDetailsController {

    @Autowired
    ProductDetailsDAO productdetailsDAO;

    @Autowired
    SalesDAO salesDAO;

    @RequestMapping(value = "/productDetails", method = RequestMethod.GET)
    public ModelAndView productDetailsView(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return new ModelAndView("redirect:/"); // Redirect to login page if user is not logged in
        }
        return new ModelAndView("saledetails");
    }


    @RequestMapping(value = "/productDetails/fetch/{productCode}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ProductDetails getProductDetails(@PathVariable("productCode") String productCode, HttpSession session) {
        
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
        
            return null;
        }

        ProductDetails productdetails = productdetailsDAO.getProductDetailsByProductCode(productCode);

        return productdetails;
    }

    @RequestMapping(value = "/sales", method = RequestMethod.POST)
    @ResponseBody
    public String insertSalesData(@RequestBody Sales sales, HttpSession session) {
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
           
            return "{\"error\": \"Unauthorized access\"}";
        }

        try {
           
            Date now = new Date();
            UserResponseDTO user = (UserResponseDTO) session.getAttribute("loggedInUser");
            sales.setAccounts_id(user.getId());
            sales.setPurchase_date(now);
            Integer saleId = salesDAO.insertSales(sales);

            if(saleId != null) {
            	session.setAttribute("saleId", saleId);
            }
         
            return "{\"success\": \"Sales data inserted successfully\"}";
        } catch (Exception e) {
            return "{\"error\": \"Error inserting sales data\", \"message\": \"" + e.getMessage() + "\"}";
        }
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    @ResponseBody
    public String printSalesData(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            
            return "{\"error\": \"Unauthorized access\"}";
        }

        

        return "{\"success\": \"Sales data printed successfully\"}";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void generateReport(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		HttpSession session
    		
    		
    )throws IOException {
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            
            return;
        }

        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/WEB-INF/reports/slip.jrxml");
        Integer saleId = (Integer) session.getAttribute("saleId");
        
        JRBeanCollectionDataSource source = null;
        JasperReport jasperReport;
        JasperPrint print;
        ArrayList<SlipReport> list = (ArrayList<SlipReport>) salesDAO.getPrintData(saleId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Slip");

        try {
            source = new JRBeanCollectionDataSource(list);

            jasperReport = JasperCompileManager.compileReport(path);

            print = JasperFillManager.fillReport(jasperReport, parameters, source);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=slip.pdf");

            JRPdfExporter exporterPdf = new JRPdfExporter();
            exporterPdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
            exporterPdf.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            exporterPdf.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
