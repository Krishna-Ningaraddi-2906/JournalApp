package com.springbootProjects.JournalApp.Services.UserService;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.JournalEntryRepository.JournalEntityRepository;
import com.springbootProjects.JournalApp.Repository.UserRepository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices
{
    @Autowired
    private UserRepository UserRepository;

    public void addEntry(UserEntity UserEntity)
    {
        UserRepository.save(UserEntity);
    }

    public List<UserEntity> getAll()
    {
        return UserRepository.findAll();
    }

    public UserEntity findByName(String userName)
    {
        return UserRepository.findByuserName(userName);
    }

    public void deleteById(ObjectId id)
    {
        UserRepository.deleteById(id);
    }
}
