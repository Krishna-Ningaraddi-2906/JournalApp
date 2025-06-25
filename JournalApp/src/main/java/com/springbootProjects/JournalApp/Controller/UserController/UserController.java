package com.springbootProjects.JournalApp.Controller.UserController;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Repository.UserRepository.UserRepository;
import com.springbootProjects.JournalApp.Services.UserService.UserServices;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserServices UserServices;
    @Autowired
    private UserRepository UserRepository;


    // AS of now i have removed the All users and we'll add this feature in admin
//    @GetMapping
//    public ResponseEntity<List<UserEntity>> getAll()
//    {
//        List<UserEntity> all= UserServices.getAll();
//        if(all!=null && all.isEmpty())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(all,HttpStatus.OK);
//    }


// This is a public API moved it to PublicController

//    @PostMapping
//    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity entry)
//    {
//        UserServices.addEntry(entry);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//   }



   @PutMapping
   public ResponseEntity<?> updatePasswordByName(@RequestBody UserEntity entry)
   {
       // When ever we hit the this api with user and pwd in authentication option of postman those details
       // can be captured here
       // At this line itself it checks the password and allows access if matching else throws forbidden
       Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName(); // here we get the user name entered
       UserEntity userInD = UserServices.findByName(userName);
       userInD.setUserName(entry.getUserName());
       userInD.setPassword(entry.getPassword());
       UserServices.addEntry(userInD);
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserName()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
