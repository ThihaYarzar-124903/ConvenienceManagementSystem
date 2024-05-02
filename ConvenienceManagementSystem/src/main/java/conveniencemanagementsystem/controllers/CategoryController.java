package conveniencemanagementsystem.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import conveniencemanagementsystem.models.CategoryBean;
import conveniencemanagementsystem.persistant.dao.CategoryDAO;
import conveniencemanagementsystem.persistant.dao.CategoryMapper;
import conveniencemanagementsystem.persistant.dto.CategoryRequestDTO;
import conveniencemanagementsystem.persistant.dto.CategoryResponseDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

@Controller
@RequestMapping("/categorys")
public class CategoryController {
	@Autowired
	CategoryDAO  categoryDAO;
	@Autowired
	CategoryMapper categorymapper;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayCategory(HttpSession session, ModelMap m) {
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRoles_id() != 1) {
            return "redirect:/";
        }

        List<CategoryResponseDTO> dtos = categoryDAO.getAllCategory();
        List<CategoryBean> categorys = categorymapper.mapToListBean(dtos);
        
        m.addAttribute("categorys", categorys);
        return "displaycategory";
    }
	
	@RequestMapping(value="/add",method=RequestMethod.GET)	
	public ModelAndView addCategory(ModelMap model) {	
		
	return new ModelAndView("addcategory","category",new CategoryBean());
	
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)	
	public String addCategory(@ModelAttribute("category")@Validated CategoryBean category,BindingResult bResult,ModelMap model) {	
		if(bResult.hasErrors()) { 
			return "addcategory"; 
		} 
		
		CategoryRequestDTO dto=categorymapper.mapToRequestDTO(category); 	
		int rs=categoryDAO.addCategory(dto);	
		if(rs==0) {	
			model.addAttribute("error","Insert Fail(SQL Error)");	
			return "addcategory"; 	
		}	
		
		model.addAttribute("result",rs);
		return "addcategory";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable int id,ModelMap model) {
		CategoryResponseDTO dto=categoryDAO.getCategoryById(id); 
		CategoryBean updatedCategory=categorymapper.mapToBean(dto); 
		return new ModelAndView("editcategory","category",updatedCategory);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCategory(@ModelAttribute("category") @Validated CategoryBean category,BindingResult bResult,ModelMap model) {
		if(bResult.hasErrors()) {
			return "editcategory";	
		}
		
		CategoryRequestDTO dto=categorymapper.mapToRequestDTO(category);		
		int rs=categoryDAO.editCategory(dto);
		if(rs==0) {
			model.addAttribute("error","Update Fail(SQL Error)");
			return "editcategory"; 
		}
		
		model.addAttribute("result",rs);
		return "editcategory";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteCategory(@PathVariable int id,ModelMap model) {
						
		int result = categoryDAO.deleteCategory(id);
		if(result == 0) {
			
			model.addAttribute("error","Delete Fail(SQL Error)");
			return "displaycategory"; 
			
		}
		
		return "redirect:/categorys/";			
	}

//	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	            throws ServletException, IOException {
//	       CategoryDAO categoryDAO = new CategoryDAO();
//	       List<CategoryBean> categorys = categoryDAO.getAllCategories();
//	       request.setAttribute("categorys", categorys);
//	       request.getRequestDispatcher("/WEB-INF/views/categorys.jsp").forward(request, response);
//	  }
}
