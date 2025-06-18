package com.springbootProjects.JournalApp.Services.JournalEntityService;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.JournalEntryRepository.JournalEntityRepository;
import com.springbootProjects.JournalApp.Services.UserService.UserServices;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntityServices
{
    @Autowired
    private JournalEntityRepository JournalEntityRepository;

    @Autowired
    private UserServices UserServices;

    public void addEntry(JournalEntity JournalEntity, String userName)
    {
        UserEntity user= UserServices.findByName(userName); // here i am finding the user details as per the userName received
        JournalEntity saved=JournalEntityRepository.save(JournalEntity); // here i am saving the saved entries in variable
        user.getJournalEntries().add(saved); // means in UserEntity we have journalEntries there we are storing the data of journal entries
        UserServices.addEntry(user); // we have updating the journalEntries in the UserEntity
    }

    // This is the overloaded method of  the addEntry where we are utilizing it for updating
    public void addEntry(JournalEntity journalEntity)
    {
        JournalEntityRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll()
    {
        return JournalEntityRepository.findAll();
    }

    public Optional<JournalEntity> getById(ObjectId id)
    {
        return JournalEntityRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName)
    {
        UserEntity user= UserServices.findByName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id)); // it matches the id from journalEntries i.e. from userEntity and id from JournalEntity
        // if matches it removes the that id in UserEntity
        // if we dont use this line the previous deleted record will be removed one we create the new entry but maintain the consistency we are removing it manually
        UserServices.addEntry(user);
        JournalEntityRepository.deleteById(id);
    }
}
