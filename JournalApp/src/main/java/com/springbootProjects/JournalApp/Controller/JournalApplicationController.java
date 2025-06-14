package com.springbootProjects.JournalApp.Controller;

import com.springbootProjects.JournalApp.Entity.JournalEntity;
import com.springbootProjects.JournalApp.Services.JournalEntityServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("Journal")
public class JournalApplicationController
{

    @Autowired
    private JournalEntityServices services;

    @GetMapping
    public List<JournalEntity> getEntries()
    {
        return services.getAll();
    }

    @PostMapping
    public boolean journalEntry(@RequestBody JournalEntity entry)
    {

        entry.setDate(LocalDateTime.now());
        services.addEntry(entry);
        return true;
    }


    @GetMapping("id/{myId}")
    public JournalEntity getEntryById(@PathVariable ObjectId myId)
    {
        // Here we are returning option type of data which means there might be data inside or
        // may not so we are using the else null
        return services.getById(myId).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public boolean delEntryById(@PathVariable ObjectId id)
    {
        services.deleteById(id);
        return true;

    }

    @PutMapping("id/{id}")
    public JournalEntity updateById(@PathVariable ObjectId id, @RequestBody JournalEntity newEntry)
    {
        JournalEntity oldEntry=services.getById(id).orElse(null);
        if(oldEntry!=null)
        {
            oldEntry.setTitle(newEntry.getTitle()!=null&& !newEntry.getTitle().equals("")? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")? newEntry.getContent() : oldEntry.getContent());
        }
        services.addEntry(oldEntry);
        return oldEntry;
    }


}
