package conveniencemanagementsystem.persistant.dao;

import java.util.ArrayList;
import java.util.List;

import conveniencemanagementsystem.models.UserBean;
import conveniencemanagementsystem.persistant.dto.UserRequestDTO;
import conveniencemanagementsystem.persistant.dto.UserResponseDTO;

public class UserMapper {
	public UserRequestDTO mapToRequestDTO( UserBean bean) {
		UserRequestDTO dto=new  UserRequestDTO();
		dto.setId(bean.getId());
		dto.setUsername(bean.getUsername());
		dto.setEmail(bean.getEmail());
		dto.setStaff_id(bean.getStaff_id());
		dto.setRoles_id(bean.getRoles_id());
		dto.setIs_disabled(bean.isIs_disabled());
		dto.setDob(bean.getDob());
		dto.setGender(bean.getGender());
		dto.setPhone(bean.getPhone());
		dto.setNrc(bean.getNrc());
		dto.setImage_blob(bean.getImage_blob());
		dto.setPassword(bean.getPassword());
		return dto;
	}
	
	public  UserBean mapToBean( UserResponseDTO dto) {
		UserBean bean=new  UserBean();
		 
		 bean.setId(dto.getId());
		 bean.setUsername(dto.getUsername());
		 bean.setEmail(dto.getEmail());
		 bean.setStaff_id(dto.getStaff_id());
		 bean.setRoles_id(dto.getRoles_id());
		 bean.setIs_disabled(dto.isIs_disabled());
		 bean.setDob(dto.getDob());
		 bean.setGender(dto.getGender());
		 bean.setPhone(dto.getPhone());
		 bean.setNrc(dto.getNrc());
		 bean.setProfile_image(dto.getProfile_image());
		 bean.setPassword(dto.getPassword());
		 bean.setIs_disabled(dto.isIs_disabled());
		 
		 return bean;
	}
	
	public List<UserBean> mapToListBean(List<UserResponseDTO> dtos) {
	List<UserBean> beans=new ArrayList<UserBean>();
	for(UserResponseDTO dto:dtos) {
		UserBean bean=mapToBean(dto);
		
		beans.add(bean);
	}
	return beans;
}
}
