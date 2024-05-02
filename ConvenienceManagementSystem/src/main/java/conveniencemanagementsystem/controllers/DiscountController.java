package conveniencemanagementsystem.controllers;

import java.io.UnsupportedEncodingException;
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

import conveniencemanagementsystem.models.DiscountBean;
import conveniencemanagementsystem.models.ProductBean;
import conveniencemanagementsystem.persistant.dao.DiscountDAO;
import conveniencemanagementsystem.persistant.dao.DiscountMapper;
import conveniencemanagementsystem.persistant.dao.ProductDAO;
import conveniencemanagementsystem.persistant.dao.ProductMapper;
import conveniencemanagementsystem.persistant.dto.DiscountDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;




@Controller
@RequestMapping("/discounts")
public class DiscountController {
	@Autowired
	DiscountDAO discountDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	DiscountMapper discountMapper;
	@Autowired
	ProductMapper productMapper;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView displayDiscounts(HttpSession session, ModelMap m) {
        
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRoles_id() != 1) {
            
            return new ModelAndView("redirect:/");
        }

        List<DiscountDTO> dtos = discountDAO.getAllDiscounts();
        List<DiscountBean> discounts = discountMapper.mapToListBean(dtos);
        m.addAttribute("discounts", discounts);

        return new ModelAndView("displaydiscount");
    }

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView addDiscount(ModelMap model) throws UnsupportedEncodingException {
				
		List<ProductBean> products = productMapper.mapToListBean(productDAO.getProductsByWithoutDiscount());		
		model.addAttribute("productsOptionList",products);
				
		return new ModelAndView("adddiscount","discount",new DiscountBean());
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addDiscount(@ModelAttribute("discount") @Validated DiscountBean discount,BindingResult bResult,ModelMap model) {
		if(bResult.hasErrors()) {
			System.out.println("Validate Error");
			return "adddiscount";
		}	
		
		DiscountDTO dto = discountMapper.mapToDTO(discount);
		
		
		int rs=discountDAO.addDiscount(dto);
		if(rs==0) {
			model.addAttribute("error","Insert Fail(SQL Error)");
			return "adddiscount"; 
		}
		
		model.addAttribute("result",rs);
		return "adddiscount";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editDiscount(@PathVariable int id,ModelMap model) throws UnsupportedEncodingException {
		DiscountDTO dto = discountDAO.getDiscountWithLazyById(id);
		DiscountBean updatedDiscount = discountMapper.mapToBean(dto);
		
		List<ProductBean> products=productMapper.mapToListBean(productDAO.getAllProducts());
		
		model.addAttribute("productsOptionList",products);
		
		return new ModelAndView("editdiscount","discount",updatedDiscount);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editDiscount(@ModelAttribute("discount") @Validated DiscountBean discount,BindingResult bResult,ModelMap model) throws UnsupportedEncodingException {
		if(bResult.hasErrors()) {
			
			DiscountDTO dto = discountDAO.getDiscountWithLazyById(discount.getId());
			
			@SuppressWarnings("unused")
			DiscountBean updatedDiscount = discountMapper.mapToBean(dto);
			
			List<ProductBean> products = productMapper.mapToListBean(productDAO.getAllProducts());
			
			model.addAttribute("productsOptionList",products);
			
			return "editdiscount";
			
		}
		
		DiscountDTO dto=discountMapper.mapToDTO(discount);		
		int rs=discountDAO.editDiscount(dto);
		if(rs==0) {
			model.addAttribute("error","Update Fail(SQL Error)");
			return "editdiscount"; 
		}
		
		model.addAttribute("result",rs);
		return "editdiscount";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteDiscount(@PathVariable int id,ModelMap model) {
						
		int result = discountDAO.deleteDiscount(id);
		if(result == 0) {
			model.addAttribute("error","Delete Fail(SQL Error)");
			return "displaydiscount"; 
		}
		
		return "redirect:/discounts/";		
	}
}
