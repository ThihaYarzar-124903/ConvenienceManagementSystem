package conveniencemanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import conveniencemanagementsystem.persistant.dao.CategoryDAO;
import conveniencemanagementsystem.persistant.dto.CategoryResponseDTO;

@Controller
@RequestMapping("/categories")
public class ViewCategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewCategory(ModelMap model) {
        List<CategoryResponseDTO> dtos = categoryDAO.getAllCategory();
        model.addAttribute("categorys", dtos);
        
        return "viewcategory";
        
    }
   
}

