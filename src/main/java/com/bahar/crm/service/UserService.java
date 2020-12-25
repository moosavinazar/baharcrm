package com.bahar.crm.service;

import com.bahar.crm.domain.User;
import com.bahar.crm.repository.UserRepository;
import com.bahar.crm.service.dto.UserDTO;
import com.bahar.crm.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService, ServiceInterface<UserDTO>{

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        logger.debug("Request to save User : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        logger.debug("Request to update User : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        logger.debug("Request to get all Users");
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        logger.debug("Request to get User : {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmailOrCellPhoneAndEnabledIsTrueAndLockedIsFalseAndExpiredAfter(s, s, s, ZonedDateTime.now().toInstant());
    }
}
