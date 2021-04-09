package com.bonnysid.bloom.configs;

import com.bonnysid.bloom.security.JwtConfigurer;
import com.bonnysid.bloom.security.JwtTokenFilter;
import com.bonnysid.bloom.services.AuthEntryPointJwt;
import com.bonnysid.bloom.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final JwtConfigurer jwtConfigurer;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final JwtTokenFilter jwtTokenFilter;

    @Autowired
    public SecurityConfig(JwtConfigurer jwtConfigurer, UserDetailsServiceImpl userDetailsServiceImpl, AuthEntryPointJwt authEntryPointJwt, JwtTokenFilter jwtTokenFilter) {
        this.jwtConfigurer = jwtConfigurer;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.authEntryPointJwt = authEntryPointJwt;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/1.0/auth/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*");
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
