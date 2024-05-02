package conveniencemanagementsystem.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import conveniencemanagementsystem.models.CategoryBean;
import conveniencemanagementsystem.models.ProductBean;
import conveniencemanagementsystem.persistant.dao.CategoryDAO;
import conveniencemanagementsystem.persistant.dao.CategoryMapper;
import conveniencemanagementsystem.persistant.dao.ProductDAO;
import conveniencemanagementsystem.persistant.dao.ProductMapper;
import conveniencemanagementsystem.persistant.dto.ProductDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;
import conveniencemanagementsystem.persistant.util.IdGenerator;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductMapper mapper;
	@Autowired
	CategoryMapper categoryMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayProducts(HttpSession session, ModelMap m) throws UnsupportedEncodingException {
        
        UserResponseDTO loggedInUser = (UserResponseDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null || loggedInUser.getRoles_id() != 1) {
           
            return "redirect:/";
        }

        List<ProductDTO> dtos = productDAO.getAllProducts();
        List<ProductBean> products = mapper.mapToListBean(dtos);

        m.addAttribute("products", products);
        return "displayproduct";
    }

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addProduct(ModelMap model) {

		// List<AuthorResponseDTO> dtos= authorDAO.getAuthors();

		List<CategoryBean> categorys = categoryMapper.mapToListBean(categoryDAO.getAllCategory());
		model.addAttribute("categorys", categorys);

		ProductBean bean = new ProductBean();
		bean.setProduct_code(IdGenerator.generateProductCode(productDAO.getLastProductId()));

		return new ModelAndView("addproduct", "product", bean);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") @Validated ProductBean product, BindingResult bResult,ModelMap model) throws SerialException, SQLException, IOException {
		if (bResult.hasErrors()) {
			return "addproduct";
		}

		/*
		 * String fileUrl = UUID.randomUUID() + "_" + file.getOriginalFilename();
		 * 
		 * // check file is not null if (file != null && file.getSize() > 0 &&
		 * !file.isEmpty()) { try { // File uploadDir = new
		 * File(session.getServletContext().getRealPath("/images/")); File uploadDir =
		 * new File(
		 * "C:\\Thiha-Home\\JWD47_SJWS\\JWD47-ProWorkspace\\ConvenienceManagementSystem\\src\\main\\webapp\\resources\\image"
		 * ); if (!uploadDir.exists()) { uploadDir.mkdir(); } file.transferTo(new
		 * File(uploadDir, fileUrl)); file.transferTo(Path.of(uploadDir+fileUrl)); }
		 * catch (IOException e) { e.printStackTrace();
		 * System.out.println(e.getMessage()); } }
		 * 
		 * product.setImageStr(fileUrl);
		 */
		
		product.setImage_blob(new SerialBlob(product.getMultipart().getBytes()));

		ProductDTO dto = mapper.mapToRequestDTO(product);

		int rs=productDAO.addProduct(dto);
		if(rs==0) {
			model.addAttribute("error","Insert Fail(SQL Error)");
			return "addproduct"; 
		}

		model.addAttribute("result",rs);
		return "addproduct";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable int id, ModelMap model) {
		ProductDTO dto = productDAO.getProductById(id);
		ProductBean updatedProduct = mapper.mapToBean(dto);

		List<CategoryBean> categorys = categoryMapper.mapToListBean(categoryDAO.getAllCategory());
		model.addAttribute("categorys", categorys);

		return new ModelAndView("editproduct", "product", updatedProduct);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editProduct(@ModelAttribute("product") @Validated ProductBean product, BindingResult bResult,ModelMap model) throws SerialException, SQLException, IOException {
		if (bResult.hasErrors()) {
			List<CategoryBean> categorys = categoryMapper.mapToListBean(categoryDAO.getAllCategory());
			model.addAttribute("categorys", categorys);
			return "editproduct";

		}

		/*
		 * String fileUrl = UUID.randomUUID() + "_" + file.getOriginalFilename();
		 * 
		 * // check file is not null if (file != null && file.getSize() > 0 &&
		 * !file.isEmpty()) { try { // File uploadDir = new
		 * File(session.getServletContext().getRealPath("/images/")); File uploadDir =
		 * new File(
		 * "C:\\Thiha-Home\\JWD47_SJWS\\JWD47-ProWorkspace\\ConvenienceManagementSystem\\src\\main\\webapp\\resources\\image"
		 * ); if (!uploadDir.exists()) { uploadDir.mkdir(); } file.transferTo(new
		 * File(uploadDir, fileUrl)); file.transferTo(Path.of(uploadDir+fileUrl)); }
		 * catch (IOException e) { e.printStackTrace();
		 * System.out.println(e.getMessage()); } }
		 */
		
		product.setImage_blob(new SerialBlob(product.getMultipart().getBytes()));

		ProductDTO dto = mapper.mapToRequestDTO(product);
		int rs = productDAO.editProduct(dto);
		if (rs == 0) {
			model.addAttribute("error", "Update Fail(SQL Error)");
			return "editproduct";
		}

		model.addAttribute("result",rs);
		return "editproduct";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id, ModelMap model) {

		int result = productDAO.deleteProduct(id);
		if (result == 0) {
			model.addAttribute("error", "Delete Fail(SQL Error)");
			return "displayproduct";
		}

		return "redirect:/products/";
	}
	
	@PostMapping("/updateQuantity/{productId}")
	public String updateQuantity(@PathVariable int productId, @RequestParam("newQuantity") int newQuantity) {

	    productDAO.updateProductQuantity(productId, newQuantity);

	    return "redirect:/products/";
	}
}
