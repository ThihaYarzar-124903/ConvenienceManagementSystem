package conveniencemanagementsystem.persistant.dao;

import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.models.ProductBean;
import conveniencemanagementsystem.persistant.dto.ProductDTO;

public class ProductMapper {
	public ProductDTO mapToRequestDTO(ProductBean bean) {
		
		ProductDTO dto=new ProductDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setProduct_code(bean.getProduct_code());
		dto.setProducts_categories_id(bean.getProducts_categories_id());
		dto.setOriginal_price(bean.getOriginal_price());
		dto.setSold_price(bean.getSold_price());
		dto.setProduct_qty(bean.getProduct_qty());
		dto.setImage_blob(bean.getImage_blob());
		dto.setProduct_detail(bean.getProduct_detail());
		
		return dto;
	}
	
	public ProductBean mapToBean(ProductDTO dto) {
		CategoryDAO categoryDAO=new CategoryDAO();
		
		ProductBean bean=new ProductBean();
		
		bean.setId(dto.getId());
		bean.setName(dto.getName());
		bean.setProduct_code(dto.getProduct_code());
		bean.setProducts_categories_id(dto.getProducts_categories_id());
		
		String category_name=categoryDAO.getCategoryNameById(dto.getProducts_categories_id());
		bean.setCategory_name(category_name);
		bean.setOriginal_price(dto.getOriginal_price());
		bean.setSold_price(dto.getSold_price());
		bean.setProduct_qty(dto.getProduct_qty());
		bean.setProduct_image(dto.getProduct_image());
		bean.setProduct_detail(dto.getProduct_detail());
		return bean;
	}
	
	public List<ProductBean> mapToListBean(List<ProductDTO> dtos) {
		List<ProductBean> beans=new ArrayList<ProductBean>();
		for(ProductDTO dto:dtos) {
			ProductBean bean=mapToBean(dto);
			
			beans.add(bean);
		}
		return beans;
	}
}
