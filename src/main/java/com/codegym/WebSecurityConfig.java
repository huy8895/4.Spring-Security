package com.codegym;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Lớp cấu hình Java này tạo ra một bộ lọc Servlet được gọi là springSecurityFilterChain,
 chịu trách nhiệm bảo mật tất cả (bảo vệ URL ứng dụng, xác nhận username và password,
 chuyển hướng tới form đăng nhập...) trong ứng dụng.
 *
 Phương thức Override configure(AuthenticationManagerBuilder auth) cấu hình xác thực bộ nhớ
 với thông tin đăng nhập và vai trò của người dùng.
 *
 Phương thức Override configure(HttpSecurity http) cấu hình bảo mật
 dựa trên web cho tất cả các yêu cầu HTTP.
 Theo mặc định, nó sẽ được áp dụng cho tất cả các yêu cầu,
 nhưng có thể bị hạn chế bằng cách sử dụng requestMatcher () hoặc các phương thức tương tự khác.
 *
 Url '/' không được bảo mật và có thể được truy cập bởi bất kỳ ai.
 *
 Bất kỳ URL nào bắt đầu bằng '/user' đều được bảo mật
 và chỉ những người dùng nào có vai trò 'USER' mới có thể truy cập được.
 *
 Bất kỳ URL nào bắt đầu bằng '/admin' đều được bảo mật
 và chỉ những người dùng nào có vai trò 'ADMIN' mới có thể truy cập được.

 */

@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("12345")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("12345").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user**").hasRole("USER")
                .and()
                .authorizeRequests()
                .antMatchers("/admin**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }


}
