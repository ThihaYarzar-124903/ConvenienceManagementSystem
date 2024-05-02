package conveniencemanagementsystem.persistant.dao;

import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.models.CategoryBean;
import conveniencemanagementsystem.persistant.dto.CategoryRequestDTO;
import conveniencemanagementsystem.persistant.dto.CategoryResponseDTO;


public class CategoryMapper {
	public CategoryRequestDTO mapToRequestDTO( CategoryBean bean) {
		CategoryRequestDTO dto=new  CategoryRequestDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		
		return dto;
		
	}
	
	public  CategoryBean mapToBean( CategoryResponseDTO dto) {
		 
		CategoryBean bean=new  CategoryBean();
		 bean.setId(dto.getId());
		 bean.setName(dto.getName());
		 
		 return bean;
	}
	
	public List<CategoryBean> mapToListBean(List<CategoryResponseDTO> dtos) {
	List<CategoryBean> beans=new ArrayList<CategoryBean>();
	
		for( CategoryResponseDTO dto:dtos) {
			
			CategoryBean bean=mapToBean(dto);
		
			beans.add(bean);
		}
		
		return beans;
	}
}
