package conveniencemanagementsystem.controllers;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import conveniencemanagementsystem.persistant.dao.ReportDAO;
import conveniencemanagementsystem.persistant.dto.ReportsDTO;

@SuppressWarnings("unused")
@Controller
public class ReportController {

    @Autowired
    private ReportDAO reportDAO;

    @GetMapping("/details")
    public String showSalesForm(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }
        return "reportsdetails";
    }

    @PostMapping("/detail")
    public String getSalesByDateAndAccount(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam int accountId, Model model, HttpSession session) throws SQLException {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/"; // Redirect to login page if user is not logged in
        }
        List<ReportsDTO> salesList = reportDAO.getSalesByDateRangeAndAccountId(fromDate, toDate, accountId);
        model.addAttribute("salesList", salesList);
        return "salesresult";
    }

    @GetMapping("/saleDetails")
    public String getSaleDetails(@RequestParam int saleId, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }
        ReportsDTO salesReport = reportDAO.getSaleDetailsById(saleId);
        model.addAttribute("salesReport", salesReport);
        return "salereport";
    }
}