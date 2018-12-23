package com.rest.config;

import com.rest.config.Filter.JWTAuthenticationFilter;
import com.rest.config.Filter.JWTLoginFilter;
import com.rest.config.handler.AuthenticationFailureHandler;
import com.rest.config.handler.AuthenticationSuccessHandler;
import com.rest.security.MyUserDetailsService;
import com.rest.support.AdminAuthorizedEntryPoint;
import com.rest.support.UserAuthorizedEntryPoint;

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

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
@EnableWebSecurity
public class MultiHttpSecurityConfiguration {

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private JWTAuthenticationProvider jwtAuthenticationProvider;

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private AuthenticationFailureHandler failureHandler;

        private MyUserDetailsService myUserDetailsService;

        @Autowired
        public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService){
            this.myUserDetailsService = myUserDetailsService;
        }

        /**
         * 匹配 "/" 路径，不需要权限即可访问
         * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
         * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
         * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
         * 默认启用 CSRF
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    // .antMatchers("/login","/css/**", "/js/**","/fonts/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers(
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    ).permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/[^login]/**]").hasRole("ADMIN")
                    .antMatchers("/index").hasRole("USER")
                    .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/studentListPage")
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        }

        /**
         * 添加 UserDetailsService， 实现自定义登录校验
         */
        @Override
        protected void configure(AuthenticationManagerBuilder builder) throws Exception{
            builder.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
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

        private MyUserDetailsService myUserDetailsService;

        @Autowired
        public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService){
            this.myUserDetailsService = myUserDetailsService;
        }

        /**
         * 匹配 "/" 路径，不需要权限即可访问
         * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
         * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
         * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
         * 默认启用 CSRF
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    // .antMatchers("/login","/css/**", "/js/**","/fonts/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers(
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js"
                    ).permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/[^login]/**]").hasRole("ADMIN")
                    .antMatchers("/index").hasRole("USER")
                    .and()
                    .formLogin().loginPage("/studentListPage")
                    .defaultSuccessUrl("/login")
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        }

        /**
         * 添加 UserDetailsService， 实现自定义登录校验
         */
        @Override
        protected void configure(AuthenticationManagerBuilder builder) throws Exception{
            builder.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
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
