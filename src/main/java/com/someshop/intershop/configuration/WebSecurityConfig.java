package com.someshop.intershop.configuration;


import com.someshop.intershop.repository.UserRepository;
import com.someshop.intershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean () throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/registration", "/", "/img/**", "/js/**", "/search", "/categories", "/products",
                        "/adverts/{advert}", "/css/**", "/static/**", "/orders/**", "/buckets/**", "/prod/**",
                        "/pagination/**")
                        .permitAll()
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .permitAll().defaultSuccessUrl("/")
                .usernameParameter("email").passwordParameter("password")
                .and()

                .logout().logoutSuccessUrl("/")
                .permitAll()
                .and()
                .csrf().disable();
        //<input type="hidden" name="_csrf" value="${_csrf.token}" />
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
