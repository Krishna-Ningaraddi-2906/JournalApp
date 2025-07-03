package com.springbootProjects.JournalApp.Controller;

import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Services.UserService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    UserServices userServices;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers()
    {
        List<UserEntity> allUsers = userServices.getAll();

        if(allUsers!=null && !allUsers.isEmpty())
             return new ResponseEntity<>(allUsers, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // for this API to work we need to add the admin role manually in atlas or else this wont work as it is
    // accessible only to admin
    @PostMapping("/create-admin")
    public ResponseEntity<?> createNewAdminUser(@RequestBody UserEntity user)
    {
        userServices.addNewAdminUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
