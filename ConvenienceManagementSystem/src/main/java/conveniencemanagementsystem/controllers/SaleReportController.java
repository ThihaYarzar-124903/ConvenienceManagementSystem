package conveniencemanagementsystem.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import conveniencemanagementsystem.persistant.dao.SalesDetailsDAO;
import conveniencemanagementsystem.persistant.dto.SalesReportDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

@Controller
public class SaleReportController {
    @Autowired
    private SalesDetailsDAO salesDetailsDAO;

    @RequestMapping(value="/salesreports", method = RequestMethod.GET)
    public String showDailySalesReport(Model model, HttpSession session) {
        UserResponseDTO user = (UserResponseDTO) session.getAttribute("loggedInUser");
        
        if (user == null) {            
            return "redirect:/login";
        }

        int accountId = user.getId();

        List<SalesReportDTO> salesReport = salesDetailsDAO.getSalesDetailsByAccountId(accountId);
               
        model.addAttribute("salesReport", salesReport);

        return "dailysalesreports";
    }

    @RequestMapping(value="/detail", method = RequestMethod.GET)
    public String showSaleDetails(@RequestParam("saleId") int saleId, Model model) {
        SalesReportDTO saleDetails = salesDetailsDAO.getSaleDetailsById(saleId);
        
        model.addAttribute("saleDetails", saleDetails);
        
        return "report";
    }
}
