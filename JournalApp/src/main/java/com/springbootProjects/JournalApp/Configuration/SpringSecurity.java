package com.springbootProjects.JournalApp.Configuration;
import com.springbootProjects.JournalApp.Services.UserService.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsServiceImpl UserDetailsServiceImpl;

    @Override

    protected void configure(HttpSecurity http) throws Exception
  {
      http.authorizeRequests().antMatchers("/Journal/**","/user/**").authenticated()
              .anyRequest().permitAll()
              .and()
              .httpBasic();


      // cross site request forgery
      // by default it will enabled
      // as the api we built here are stateless os the CSRF is not required
      // csrf is a cyber attack which tricks the system to submit a request which was not supposed to submit

      http.csrf().disable();
  }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
      auth.userDetailsService(UserDetailsServiceImpl).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder()
  {
      return new BCryptPasswordEncoder();
  }


}

