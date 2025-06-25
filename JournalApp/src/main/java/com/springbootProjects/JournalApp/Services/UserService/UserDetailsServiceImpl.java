package com.springbootProjects.JournalApp.Services.UserService;
import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

// this class is authentication purpose

// there is a interface called UserDetailService using this class we are
// giving the implementation for that interface
// to check that interface tap double shift and search for interface name


@Component
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    UserRepository UserRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserEntity user=UserRepository.findByUserName(username);
        if(user!=null)
        {
            UserDetails userDetails=User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRole().toArray(new String[0]))
                    .build();
            return userDetails;
        }

        throw new UsernameNotFoundException("user not found with username: "+ username);

    }
}
