package com.springbootProjects.JournalApp.Controller.JournalEntry;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Services.JournalEntityService.JournalEntityServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Journal")
public class JournalApplicationController
{

    @Autowired
    private JournalEntityServices services;

    @GetMapping
    public ResponseEntity<?> getEntries() // here ? means wild card which means we are not bound to send only
    // JournalEntity type we send any Type later wrapping in ResponseEntity<>()
    {

        List<JournalEntity> all=services.getAll();
        if(all!=null && all.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> journalEntry(@RequestBody JournalEntity entry)
    {

        // Here we are sending the Post request in try if any issue occurs it throws the exception bu returning Bad request
        try {
            entry.setDate(LocalDateTime.now());
            services.addEntry(entry);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId myId)
    {
        // Here we are returning option type of data which means there might be data inside or
        // may not so we are using the else null
        // there  is one more issue in this approach where in if we return the null still we will 200 OK response in
        // post man without any output so we use response entity to fix this issue
        //  return services.getById(myId).orElse(null);

        Optional<JournalEntity> journalEntry = services.getById(myId);
        if(journalEntry.isPresent()) { // here were are checking for the entry based on id if found we'll send 200 OK
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // here if not found we are sending 404 Not found

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<JournalEntity> delEntryById(@PathVariable ObjectId id)
    {
        services.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntity> updateById(@PathVariable ObjectId id, @RequestBody JournalEntity newEntry)
    {
        JournalEntity oldEntry=services.getById(id).orElse(null);
        if(oldEntry!=null)
        {
            oldEntry.setTitle(newEntry.getTitle()!=null&& !newEntry.getTitle().equals("")? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")? newEntry.getContent() : oldEntry.getContent());
            services.addEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
