package com.rest.security.config;

<<<<<<< HEAD:src/main/java/com/rest/config/MultiHttpSecurityConfiguration.java
import com.rest.config.Filter.JWTAuthenticationFilter;
import com.rest.config.Filter.JWTLoginFilter;
import com.rest.config.handler.AuthenticationFailureHandler;
import com.rest.config.handler.AuthenticationSuccessHandler;
import com.rest.support.AdminAuthorizedEntryPoint;
import com.rest.support.UserAuthorizedEntryPoint;

=======
import com.rest.security.Filter.JWTAuthenticationFilter;
import com.rest.security.Filter.JWTLoginFilter;
import com.rest.security.JWTAuthenticationProvider;
import com.rest.security.MyUserDetailsService;
import com.rest.security.handler.AuthenticationFailureHandler;
import com.rest.security.handler.AuthenticationSuccessHandler;
>>>>>>> jwt:src/main/java/com/rest/security/config/MultiHttpSecurityConfig.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class MultiHttpSecurityConfig {

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private JWTAuthenticationProvider jwtAuthenticationProvider;

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private AuthenticationFailureHandler failureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.exceptionHandling().
                    authenticationEntryPoint(new AdminAuthorizedEntryPoint());
            http.addFilter(jwtLoginFilter())
                    .addFilterBefore(jwtAuthenticationFilter(), JWTLoginFilter.class);
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/login").permitAll()
                    .anyRequest().hasRole("ADMIN");
            http.formLogin()
                    .loginPage("/login").loginProcessingUrl("/login")
                    .usernameParameter("account").passwordParameter("password")
                    .successHandler(successHandler).failureHandler(failureHandler)
                    .permitAll();
            http.csrf().disable();
            http.cors();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //auth.userDetailsService(adminUserDetailsService).passwordEncoder(passwordEncoder());
            auth.authenticationProvider(jwtAuthenticationProvider);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public JWTLoginFilter jwtLoginFilter() throws Exception {
            JWTLoginFilter loginFilter = new JWTLoginFilter();
            loginFilter.setAuthenticationManager(authenticationManager());
            return loginFilter;
        }

        @Bean
        public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
            return new JWTAuthenticationFilter(authenticationManager());
        }
    }

    @Configuration
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private JWTAuthenticationProvider jwtAuthenticationProvider;

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private AuthenticationFailureHandler failureHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.exceptionHandling().
                    authenticationEntryPoint(new UserAuthorizedEntryPoint());
            http.addFilter(userJWTLoginFilter())
                    .addFilterBefore(userJWTAuthenticationFilter(), JWTLoginFilter.class);
            http.authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/test/**").permitAll()
                    .anyRequest().authenticated();
            http.formLogin()
                    .loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/studentListPage")
                    .usernameParameter("account").passwordParameter("password")
                    .successHandler(successHandler).failureHandler(failureHandler)
                    .permitAll();
            http.csrf().disable();
            http.cors();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jwtAuthenticationProvider);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }

        @Bean
        public JWTLoginFilter userJWTLoginFilter() throws Exception {
            JWTLoginFilter loginFilter = new JWTLoginFilter();
            loginFilter.setAuthenticationManager(authenticationManager());
            return loginFilter;
        }

        @Bean
        public JWTAuthenticationFilter userJWTAuthenticationFilter() throws Exception {
            return new JWTAuthenticationFilter(authenticationManager());
        }

    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public static CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
