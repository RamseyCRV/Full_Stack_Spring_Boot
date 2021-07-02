package com.libra;

import com.libra.Config.LibraConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/errorSignUp",
                        "/resources/**","/avatar/**","/css/**",
                        "/icons/**","/images/**", "/Products/**",  "/js/**")
                .permitAll()
                .antMatchers(
                        "/signIn", "/signUp" ,"/resources/**"
                        ,"/avatar/**","/css/**","/icons/**","/images/**",
                        "/Products/**",  "/js/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage(LibraConstants.InitConstants.URL_SIGN_IN)
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
                    {
                        redirectStrategy.sendRedirect(request, response, LibraConstants.NewsConstants.URL_NEWS);
                    }
                }).permitAll().and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher(LibraConstants.InitConstants.URL_SIGN_OUT))
                .logoutSuccessUrl(LibraConstants.InitConstants.URL_SIGN_IN).permitAll()
                .and().logout();
    }

}
