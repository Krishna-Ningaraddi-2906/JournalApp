package com.springbootProjects.JournalApp.Controller.JournalEntry;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Services.JournalEntityService.JournalEntityServices;
import com.springbootProjects.JournalApp.Services.UserService.UserServices;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
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

    @Autowired
    private UserServices UserServices;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAlEntriesOfUser(@PathVariable String userName) // here ? means wild card which means we are not bound to send only
    // JournalEntity type we send any Type later wrapping in ResponseEntity<>()
    {

        // Here we are finding the user by taking the username from path Variable
        UserEntity user= UserServices.findByName(userName);
        System.out.println(user.getJournalEntries());
        // For that specific user we are finding the journal Entries and storing in the list
        List<JournalEntity> all= user.getJournalEntries(); // here i am calling the getter of userEntity
        if(all!=null && all.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntity> journalEntry(@RequestBody JournalEntity entry, @PathVariable String userName)
    {

        // Here we are sending the Post request in try if any issue occurs it throws the exception but returning Bad request
        try {
            entry.setDate(LocalDateTime.now());
            services.addEntry(entry,userName); // here we are sending the userName to addEntry
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        catch (Exception e) {
            e.printStackTrace(); // This tells us what’s failing
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

    @DeleteMapping("id/{userName}/{id}")
    public ResponseEntity<JournalEntity> delEntryById(@PathVariable ObjectId id, @PathVariable String userName)
    {
        //Here when we delete the entry based on id it will be deleted in the JournalEntity collection but its reference will not deleted in UserEntity
        // We need to manually update it
        services.deleteById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("id/{userName}/ {id}")
    public ResponseEntity<JournalEntity> updateById(@PathVariable ObjectId id, @RequestBody JournalEntity newEntry)
    {
        JournalEntity oldEntry=services.getById(id).orElse(null);
        if(oldEntry!=null)
        {
            oldEntry.setTitle(newEntry.getTitle()!=null&& !newEntry.getTitle().equals("")? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")? newEntry.getContent() : oldEntry.getContent());
            services.addEntry(oldEntry); // here we are calling the overloaded method of addEntry with only one parameter
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
