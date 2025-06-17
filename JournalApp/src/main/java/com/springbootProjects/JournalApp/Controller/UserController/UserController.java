package com.springbootProjects.JournalApp.Controller.UserController;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Services.UserService.UserServices;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserServices UserServices;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll()
    {
        List<UserEntity> all= UserServices.getAll();
        if(all!=null && all.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity entry)
    {
        UserServices.addEntry(entry);
        return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @PutMapping("/{userName}")
   public ResponseEntity<?> updatePasswordByName(@RequestBody UserEntity entry,@PathVariable String userName)
   {
     UserEntity userInD = UserServices.findByName(userName);

     if(userInD!=null)
     {
         userInD.setUserName(entry.getUserName());
         userInD.setPassword(entry.getPassword());
         UserServices.addEntry(userInD);
         return new ResponseEntity<>(userInD,HttpStatus.OK);
     }
     else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
