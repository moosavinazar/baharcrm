package com.bahar.crm.controller;

import com.bahar.crm.service.UserService;
import com.bahar.crm.service.dto.UserDTO;
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
@RequestMapping("api/v1/user/")
public class UserController implements ControllerInterface<UserDTO> {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${sar.app.name}")
    private String appName;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_CREATE_USER')")
    public ResponseEntity<UserDTO> create(UserDTO userDTO) throws URISyntaxException {

        logger.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            logger.warn("To create User the id must be null");
            return  ResponseEntity.badRequest()
                    .header(appName, "To create User the id must be null")
                    .body(userDTO);
        }

        UserDTO result = userService.save(userDTO);

        return ResponseEntity.created(
                new URI("/api/v1/user/get/" + result.getId()))
                .header(appName, "New user created with id : " + result.getId())
                .body(result);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_UPDATE_USER')")
    public ResponseEntity<UserDTO> update(UserDTO userDTO) throws URISyntaxException {

        logger.info("REST request to update User : {}", userDTO);

        if (userDTO.getId() == null) {
            logger.warn("To update User the id must not be null");
            return  ResponseEntity.badRequest()
                    .header(appName, "To update User the id must not be null")
                    .body(userDTO);
        }

        UserDTO user = userService.update(userDTO);

        return ResponseEntity.ok()
                .header(appName, "User updated with id : " + user.getId())
                .body(user);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_VIEW_USER_LIST')")
    public ResponseEntity<Page<UserDTO>> getAll(Pageable pageable) {

        logger.debug("REST request to get a page of Users");

        Page<UserDTO> result = userService.findAll(pageable);

        // TODO must add pagination header

        return ResponseEntity.ok()
                .header(appName, "success")
                .body(result);

    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_VIEW_USER')")
    public ResponseEntity<UserDTO> get(Long id) {

        logger.debug("REST request to get User : {}", id);

        Optional<UserDTO> result = userService.findOne(id);

        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound()
                    .header(appName, "User with id " + id + " not exist")
                    .build();
        }

        return ResponseEntity.ok()
                .header(appName, "success")
                .body(result.get());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('OP_DELETE_USER')")
    public ResponseEntity<Void> deleteFactor(Long id) {

        logger.info("REST request to delete User : {}", id);

        Optional<UserDTO> result = userService.findOne(id);

        if (result == null || result.isEmpty()) {
            return ResponseEntity.notFound()
                    .header(appName, "User with id " + id + " not exist")
                    .build();
        }

        userService.delete(id);

        return ResponseEntity.noContent()
                .header(appName, "success")
                .build();
    }
}
