package com.springbootProjects.JournalApp.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter
{
  protected void configure(HttpSecurity http) throws Exception
  {
      http.authorizeHttpRequests().antMatchers("/hello").
              permitAll().anyRequest().
              authenticated().and().
              formLogin();
  }
}