package conveniencemanagementsystem.persistant.dao;

import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.models.DiscountBean;
import conveniencemanagementsystem.models.ProductBean;
import conveniencemanagementsystem.persistant.dto.DiscountDTO;
import conveniencemanagementsystem.persistant.dto.ProductDTO;


public class DiscountMapper {
	
	public DiscountDTO mapToDTO(DiscountBean bean) {
		ProductMapper product_mapper=new ProductMapper();
		
		DiscountDTO dto=new DiscountDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setDiscount_percent(bean.getDiscount_percent());
		dto.setFrom_date(bean.getFrom_date());
		dto.setTo_date(bean.getTo_date());
		
		List<ProductDTO> products = new ArrayList<ProductDTO>();	
		
		for(ProductBean product : bean.getProducts()) {
			ProductDTO product_dto = product_mapper.mapToRequestDTO(product);			
			products.add(product_dto);			
		}		
		dto.setProducts(products);
		return dto;
	}
	
	public DiscountBean mapToBean(DiscountDTO dto) {
		
		DiscountBean bean=new DiscountBean();
		bean.setId(dto.getId());
		bean.setName(dto.getName());				
		bean.setDiscount_percent(dto.getDiscount_percent());
		bean.setFrom_date(dto.getFrom_date());
		bean.setTo_date(dto.getTo_date());
		
		StringBuilder product_names = new StringBuilder();
		
		List<ProductBean> products = new ArrayList<ProductBean>();

		for(ProductDTO dto_product : dto.getProducts()) {

			ProductBean product = new ProductBean();
			
			product.setId(dto_product.getId());
			product.setName(dto_product.getName());
			product_names.append(dto_product.getName()+", ");
			product.setProduct_code(dto_product.getProduct_code());
			product.setOriginal_price(dto_product.getOriginal_price());
			product.setSold_price(dto_product.getSold_price());
			product.setProduct_qty(dto_product.getProduct_qty());
			product.setProduct_image(dto_product.getProduct_image());
			product.setOriginal_price(dto_product.getOriginal_price());
			
			products.add(product);
		}		
		bean.setProduct_names(product_names.toString());
		
		bean.setProducts(products);
		return bean;
	}
	
	public List<DiscountBean> mapToListBean(List<DiscountDTO> dtos) {
		List<DiscountBean> beans = new ArrayList<DiscountBean>();
		for(DiscountDTO dto:dtos) {
			DiscountBean bean = mapToBean(dto);
			
			beans.add(bean);
		}
		
		return beans;
	}
}
