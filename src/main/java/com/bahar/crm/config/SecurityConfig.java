package com.bahar.crm.config;

import com.bahar.crm.service.OAuth2UserService;
import com.bahar.crm.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final UserService userService;
    private final OAuth2UserService oAuth2UserService;

    public SecurityConfig(DataSource dataSource,
                          UserService userService,
                          OAuth2UserService oAuth2UserService) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().disable();*/
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2Login()
                .authorizationEndpoint().baseUri("/login/oauth2")
                .and().redirectionEndpoint().baseUri("/login/callback")
                .and().userInfoEndpoint().userService(oAuth2UserService)
                .and().successHandler(new LoginSuccessHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*auth.jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery("select username, password, email, cell_phone, enabled, locked, expired from sar_user where username=? or email=? or cell_phone=?")
                .authoritiesByUsernameQuery("select su.username, su.email, su.cell_phone, authorities from sar_user su inner join sar_user_roles sur on su.id = sur.user_id inner join role_authorities ra on sur.roles_id = ra.role_id where username=? or email=? or cell_phone=?");*/

        auth.userDetailsService(userService);

    }
}
