package projekti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DevelopmentSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
                .antMatchers("/css/**", "/javascript/**", "/media/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/EmployeeSearch").permitAll()
                .antMatchers("/EmployeeSearch/").permitAll()
                .antMatchers("/EmployeeSearch/TermsOfService").permitAll()
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Posts").permitAll()
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Register").permitAll()
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Welcome").permitAll()
                .antMatchers(HttpMethod.POST, "/EmployeeSearch/Register").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
