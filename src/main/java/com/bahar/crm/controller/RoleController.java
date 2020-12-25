package com.bahar.crm.controller;

import com.bahar.crm.service.RoleService;
import com.bahar.crm.service.dto.RoleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/role/")
public class RoleController implements ControllerInterface<RoleDTO> {

    private final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Value("${sar.app.name}")
    private String appName;

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_CREATE_ROLE')")
    public ResponseEntity<RoleDTO> create(RoleDTO roleDTO) throws URISyntaxException {

        logger.debug("REST request to save Role : {}", roleDTO);

        if (roleDTO.getId() != null) {
            logger.warn("To create Role the id must be null");
            return  ResponseEntity.badRequest()
                    .header(appName, "To create Role the id must be null")
                    .body(roleDTO);
        }

        RoleDTO result = roleService.save(roleDTO);

        return ResponseEntity.created(
                new URI("/api/v1/role/get/" + result.getId()))
                .header(appName, "New role created with id : " + result.getId())
                .body(result);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_UPDATE_ROLE')")
    public ResponseEntity<RoleDTO> update(RoleDTO roleDTO) throws URISyntaxException {

        logger.info("REST request to update Role : {}", roleDTO);

        if (roleDTO.getId() == null) {
            logger.warn("To update Role the id must not be null");
            return  ResponseEntity.badRequest()
                    .header(appName, "To update Role the id must not be null")
                    .body(roleDTO);
        }

        RoleDTO role = roleService.update(roleDTO);

        return ResponseEntity.ok()
                .header(appName, "Role updated with id : " + role.getId())
                .body(role);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_VIEW_ROLE_LIST')")
    public ResponseEntity<Page<RoleDTO>> getAll(Pageable pageable) {

        logger.debug("REST request to get a page of Roles");

        Page<RoleDTO> result = roleService.findAll(pageable);

        // TODO must add pagination header

        return ResponseEntity.ok()
                .header(appName, "success")
                .body(result);

    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_VIEW_ROLE')")
    public ResponseEntity<RoleDTO> get(Long id) {

        logger.debug("REST request to get Role : {}", id);

        Optional<RoleDTO> result = roleService.findOne(id);

        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound()
                    .header(appName, "Role with id " + id + " not exist")
                    .build();
        }

        return ResponseEntity.ok()
                .header(appName, "success")
                .body(result.get());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_DELETE_ROLE')")
    public ResponseEntity<Void> deleteFactor(Long id) {

        logger.info("REST request to delete Role : {}", id);

        Optional<RoleDTO> result = roleService.findOne(id);

        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound()
                    .header(appName, "Role with id " + id + " not exist")
                    .build();
        }

        roleService.delete(id);

        return ResponseEntity.noContent()
                .header(appName, "success")
                .build();
    }
}
