package com.ken.infinity.configurations;

import com.ken.infinity.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public HttpFirewall allowUrlEncodedDoubleSlashFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true); // 🔓 Allow encoded double slashes
        return firewall;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(allowUrlEncodedDoubleSlashFirewall());
    }

    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http.csrf().disable();
    //        http.authorizeRequests().antMatchers("/", "/login", "/homepage", "/register").permitAll();
    //        http.authorizeRequests()
    //                .and()
    //                .formLogin()
    //                .loginPage("/login")
    //                .defaultSuccessUrl("/homepage", true) // 🔁 Redirect to homepage
    //                .failureUrl("/login?error=true")
    //                .and()
    //                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    //    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/", "/login", "/homepage", "/register", "/img/**", "/css/**", "/js/**", "/bootstrap/**")
            .permitAll() // ✅ Allow static resources
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/homepage", true)
            .failureUrl("/login?error=true")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");
    }
    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http
    //            .csrf().disable()
    //            .authorizeRequests()
    //            .antMatchers("/addArt").permitAll()  // 👈 Allow /addArt without login
    //            .anyRequest().authenticated()
    //            .and()
    //            .formLogin()
    //            .loginPage("/login").permitAll()
    //            .and()
    //            .logout().permitAll();
    //    }

}
