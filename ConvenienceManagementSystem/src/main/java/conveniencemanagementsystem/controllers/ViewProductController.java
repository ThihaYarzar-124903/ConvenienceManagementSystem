package conveniencemanagementsystem.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import conveniencemanagementsystem.persistant.dao.ProductDAO;
import conveniencemanagementsystem.persistant.dto.ProductDTO;


@Controller
@RequestMapping("/viewproduct")
public class ViewProductController {
	@Autowired
	private ProductDAO productDAO;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewProducts(ModelMap m) throws UnsupportedEncodingException {
		List<ProductDTO> dtos = productDAO.getAllProducts();
		m.addAttribute("products",dtos);
		
		return "viewproduct";
	}
}