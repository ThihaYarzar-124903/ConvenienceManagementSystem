package conveniencemanagementsystem.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import conveniencemanagementsystem.models.UserBean;
import conveniencemanagementsystem.persistant.dao.CategoryDAO;
import conveniencemanagementsystem.persistant.dao.ProductDAO;
import conveniencemanagementsystem.persistant.dao.UserDAO;
import conveniencemanagementsystem.persistant.dto.UserRequestDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

@Controller
@RequestMapping("/")
public class PageController {

    private final CategoryDAO categoryDAO;

    private final ProductDAO productDAO;

    public PageController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = new ProductDAO();
    }

    @GetMapping("/")
    public ModelAndView displayLogin() {
        return new ModelAndView("login", "user", new UserBean());
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("user") UserBean user, HttpSession session, ModelMap model) {
//        UserRequestDTO dto = new UserRequestDTO();
//        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
//        UserResponseDTO res = UserDAO.selectAllUser(dto);
//        UserResponseDTO loggedInUser = UserDAO.getUserByEmail(dto.getEmail());
//
//        if (loggedInUser != null && loggedInUser.getPassword().equals(user.getPassword())) {
//        	
//            session.setAttribute("loggedInUser", loggedInUser);
//
//            if (loggedInUser.getRoles_id() == 1) {
//                return "redirect:/owner";
//            }
//
////            if (loggedInUser.getRoles_id() == 2) {
////                model.addAttribute("loggedInUser", loggedInUser);
////                model.addAttribute("error", "Login Success!!!");
////                return "redirect:/staff";
////            }
//            
//            if (res.isIs_disabled() == false) {
//                // Handle disabled accounts
//                model.addAttribute("error", "This account is disabled.");
//                return "login";
//            } else if (loggedInUser != null && loggedInUser.getRoles_id() == 2) {
//                // Handle successfully logged in staff
//                model.addAttribute("loggedInUser", loggedInUser);
//                model.addAttribute("error", "Login Success!!!");
//                return "redirect:/staff";
//            } else {
//                // Handle other cases (e.g., invalid credentials)
//                model.addAttribute("error", "Login Failed!!!");
//                return "login";
//            }
//            
//            
//        }
//
//        model.addAttribute("error", "Login Failed!!!");
//        return "login";
//    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserBean user, HttpSession session, ModelMap model) {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        UserResponseDTO loggedInUser = UserDAO.getUserByEmail(dto.getEmail());

        if (loggedInUser != null && loggedInUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("loggedInUser", loggedInUser);
            if (loggedInUser.getRoles_id() == 1) {
                
                return "redirect:/owner";
            } else if (loggedInUser.getRoles_id() == 2) {
               
                if (loggedInUser.isIs_disabled()) {
                   
                    model.addAttribute("error", "This account is disabled.");
                    return "login"; 
                } else {
                    return "/staff";
                }
            }
        } else {
            // Authentication failed
            model.addAttribute("error", "Invalid email or password.");
            return "login"; 
        }
       
        model.addAttribute("error", "Invalid email or password.");
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirect) {
        session.invalidate();
        redirect.addFlashAttribute("success", "Logout Successfully!");
        return "redirect:/";
    }

    @GetMapping("/owner")
    public String owner(HttpSession session, ModelMap model) {
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");

        if (loggedInUser != null && loggedInUser.getRoles_id() == 1) {
            model.addAttribute("categoryCount", categoryDAO.getCategoriesCount());
            model.addAttribute("productCount", productDAO.getProductsCount());
            return "owner";
        } else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/staff")
    public String staffPage() {
        return "staff";
    }
}
