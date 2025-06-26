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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Journal")
public class JournalApplicationController
{

    @Autowired
    private JournalEntityServices services;

    @Autowired
    private UserServices UserServices;

    @GetMapping
    public ResponseEntity<?> getAlEntriesOfUser() // here ? means wild card which means we are not bound to send only
    // JournalEntity type we send any Type later wrapping in ResponseEntity<>()
    {

        // Here we are doing authentication instead of talking userName from pathVariable
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user= UserServices.findByName(userName);
        System.out.println(user.getJournalEntries());
        // For that specific user we are finding the journal Entries and storing in the list
        List<JournalEntity> all= user.getJournalEntries(); // here i am calling the getter of userEntity
        if(all!=null && all.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> journalEntry(@RequestBody JournalEntity entry)
    {

        // Here we are sending the Post request in try if any issue occurs it throws the exception but returning Bad request
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
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
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=UserServices.findByName(userName);
        // Here first we are fetching the userName and with which we are finding the journal entry id in DB
        // later matching that to user entered id is it matching or not by applying the filter
        List<JournalEntity> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            Optional<JournalEntity> JournalEntity=services.getById(myId);
            if(JournalEntity.isPresent())
            {
                return new ResponseEntity<>(JournalEntity.get(),HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<JournalEntity> delEntryById(@PathVariable ObjectId id)
    {
        //Here when we delete the entry based on id it will be deleted in the JournalEntity collection but its reference will not deleted in UserEntity
        // We need to manually update it
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean removed=services.deleteById(id,userName);

        if(removed)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntity> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=UserServices.findByName(userName);
        JournalEntity oldEntry=services.getById(myId).orElse(null);
        List<JournalEntity> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            Optional<JournalEntity> JournalEntity=services.getById(myId);
            if(JournalEntity.isPresent())
            {
                oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent()!=null&& !newEntry.getContent().equals("")? newEntry.getContent() : oldEntry.getContent());
                services.addEntry(oldEntry); // here we are calling the overloaded method of addEntry with only one parameter
                return new ResponseEntity<>(oldEntry,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
