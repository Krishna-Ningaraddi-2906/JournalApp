package com.springbootProjects.JournalApp.Service;

// Here we are doing Mock testing using Mockito
// we use mockito because in unit test when ever do test the entire application will run
// if there is a big application then it will take time to run the application
// using mockito we can mock the parts repository parts without running the entire application


import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.UserRepository.UserRepository;
import com.springbootProjects.JournalApp.Services.UserService.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;



import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class UserDetailsServiceImplTest
{
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;


    // UserRepository will be null we are playing outside the application context so we need to initialize
    // the userRepository so we use MockitoAnnotations annotation
    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest()
    {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(UserEntity.builder().userName("ram").password("inrinrick").role(new ArrayList<>()).build());
        UserDetails user=userDetailsService.loadUserByUsername("krishna");
        Assertions.assertNotNull(user);
    }
}

// Here we are testing the loadUserByUsername() method without fetching the data from the database
