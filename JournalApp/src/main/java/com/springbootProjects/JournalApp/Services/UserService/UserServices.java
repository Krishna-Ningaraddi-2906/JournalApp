package com.springbootProjects.JournalApp.Services.UserService;

import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.UserRepository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserServices
{
    @Autowired
    private UserRepository UserRepository;

    private  final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public void addNewEntry(UserEntity UserEntity)
    {
        UserEntity.setPassword(passwordEncoder.encode(UserEntity.getPassword()));
        UserEntity.setRole(Arrays.asList("USER")); // here we are hard coding the role as user
        UserRepository.save(UserEntity);
    }

    public void addNewAdminUser(UserEntity userEntity)
    {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(Arrays.asList("USER","ADMIN")); // here we are hard coding the role as user
        UserRepository.save(userEntity);
    }

    // Previously while adding the entry to journalEntries collection we used to use above method was encoding the encoded password
    // so we where getting the unauthorised, so we created the new method
    public void addUser(UserEntity UserEntity)
    {
        UserRepository.save(UserEntity);
    }

    public List<UserEntity> getAll()
    {
        return UserRepository.findAll();
    }

    public UserEntity findByName(String userName)
    {
        return UserRepository.findByUserName(userName);
    }

    public void deleteById(ObjectId id)
    {
        UserRepository.deleteById(id);
    }
}
