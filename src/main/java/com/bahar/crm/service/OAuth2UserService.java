package com.bahar.crm.service;

import com.bahar.crm.repository.RoleRepository;
import com.bahar.crm.repository.UserRepository;
import com.bahar.crm.domain.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public OAuth2UserService(RoleRepository roleRepository,
                             UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        User user = userRepository.findByEmail(oAuth2User.getAttribute("email"));

        if (user == null) {
            user = new User();
            user.setEmail(oAuth2User.getAttribute("email"));
            user.setPassword("123456");
            user.setLocked(false);
            user.setCredential(true);
            user.setEnabled(true);
            user.getRoles().add(roleRepository.getOne(1L));
        }

        return userRepository.save(user);
    }
}
