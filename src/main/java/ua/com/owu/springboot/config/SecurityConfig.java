package ua.com.owu.springboot.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import ua.com.owu.springboot.repositories.UserRepository;
import ua.com.owu.springboot.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // і тут я поплив(( буду переробляти

    private UserRepository userRepository;

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .antMatchers("/public/**").permitAll()
//                        .antMatchers("/user/**").hasRole("BUYER")
//                        .antMatchers("/seller/**").hasRole("SELLER")
//                        .antMatchers("/manager/**").hasRole("MANAGER")
//                        .antMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .permitAll()
//                );
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }
}