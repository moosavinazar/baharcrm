package com.bahar.crm.service;

import com.bahar.crm.repository.RoleRepository;
import com.bahar.crm.service.dto.RoleDTO;
import com.bahar.crm.service.mapper.RoleMapper;
import com.bahar.crm.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService implements ServiceInterface<RoleDTO>{

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository,
                       RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        logger.debug("Request to save Role : {}", roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        logger.debug("Request to update Role : {}", roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public Page<RoleDTO> findAll(Pageable pageable) {
        logger.debug("Request to get all Roles");
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map(roleMapper::toDto);
    }

    @Override
    public Optional<RoleDTO> findOne(Long id) {
        logger.debug("Request to get Role : {}", id);
        Optional<Role> role = roleRepository.findById(id);
        return role.map(roleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete Role : {}", id);
        roleRepository.deleteById(id);
    }
    
}
