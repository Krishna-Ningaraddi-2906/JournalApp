package com.springbootProjects.JournalApp.Services.JournalEntityService;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Repository.JournalEntryRepository.JournalEntityRepository;
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

    public void addEntry(JournalEntity JournalEntity)
    {
        JournalEntityRepository.save(JournalEntity);
    }

    public List<JournalEntity> getAll()
    {
        return JournalEntityRepository.findAll();
    }

    public Optional<JournalEntity> getById(ObjectId id)
    {
        return JournalEntityRepository.findById(id);
    }

    public void deleteById(ObjectId id)
    {
        JournalEntityRepository.deleteById(id);
    }
}
