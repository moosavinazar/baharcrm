package com.bahar.crm.service.mapper;

import com.bahar.crm.service.dto.RoleDTO;
import com.bahar.crm.domain.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}
