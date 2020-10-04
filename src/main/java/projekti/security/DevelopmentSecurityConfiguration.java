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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class DevelopmentSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/javascript/**", "/media/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/EmployeeSearch").permitAll()
                .antMatchers("/EmployeeSearch/").permitAll()
                .antMatchers("/EmployeeSearch/Login").permitAll()
                .antMatchers("/EmployeeSearch/TermsOfService").permitAll()
                .antMatchers("/EmployeeSearch/Users").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Posts").permitAll()
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Register").permitAll()
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Register/**").permitAll()
                .antMatchers(HttpMethod.GET, "/EmployeeSearch/Welcome").permitAll()
                .antMatchers(HttpMethod.POST, "/EmployeeSearch/Register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/EmployeeSearch/Login")
                .failureUrl("/EmployeeSearch/LoginError")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/EmployeeSearch/Logout"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/EmployeeSearch/Welcome");
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
