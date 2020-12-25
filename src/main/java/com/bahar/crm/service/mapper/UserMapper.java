package com.bahar.crm.service.mapper;


import com.bahar.crm.domain.User;
import com.bahar.crm.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
