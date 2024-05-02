package conveniencemanagementsystem.controllers;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import conveniencemanagementsystem.models.UserBean;
import conveniencemanagementsystem.persistant.dao.UserDAO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

@Controller	
public class OwnerController {	
    @SuppressWarnings("unused")	
	@Autowired
    private UserDAO userDAO;
    @Autowired
    private UserDAO salerdao;
    
    @GetMapping("/manageSaleStaff")
    public String userRegister(HttpSession session, Model model) {
        // Check if the user is logged in and has the required role
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRoles_id() != 1) {
            // Redirect to the login page or handle unauthorized access
            return "redirect:/";
        }

        model.addAttribute("userBean", new UserBean());
        return "manageSaleStaff";
    }

    @GetMapping("/saleStaffRegister")
    public ModelAndView salerRegister(HttpSession session, Model model) {
        // Check if the user is logged in and has the required role
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRoles_id() != 1) {
            // Redirect to the login page or handle unauthorized access
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("saleStaffRegister", "user", new UserBean());
    }
		/*
		 * @PostMapping("/saleStaffRegister") public String
		 * userRegister(@ModelAttribute("SaleStaffBean") @Validated SaleStaffBean
		 * saleStaffBean, BindingResult br, Model model) { if (br.hasErrors()) { return
		 * "registration"; } else if
		 * (!saleStaffBean.getPassword().equals(saleStaffBean.getPassword())) {
		 * model.addAttribute("passwordError", "Password doesn't match!"); return
		 * "registration"; } else if (UserHelper.isEmailExist(saleStaffBean.getEmail()))
		 * { model.addAttribute("error", "Email already exists."); return
		 * "registration"; } else { UserRequestDTO dto = new UserRequestDTO(); // Set
		 * dto properties from userBean dao.insertUser(dto);
		 * model.addAttribute("success", "Successfully registered."); return
		 * "redirect:/viewsaleStaffInformation"; } }
		 */
	 
	 
	@SuppressWarnings("static-access")
	@GetMapping("/viewSaleStaffInformation")
	    public String viewSaleStaffInformation(HttpSession session, Model model) throws UnsupportedEncodingException {
	        // Check if the user is logged in and has the required role
	        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
	        if (loggedInUser == null || loggedInUser.getRoles_id() != 1) {
	            // Redirect to the login page or handle unauthorized access
	            return "redirect:/";
	        }

	        model.addAttribute("userBean", new UserBean());
	        List<UserResponseDTO> staff = salerdao.getAllUser();
	        model.addAttribute("staff", staff);

	        return "viewSaleStaffInformation";
	}
}
