package com.springbootProjects.JournalApp.Controller.UserController;

import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import com.springbootProjects.JournalApp.Services.UserService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController
{
    @Autowired
    private UserServices UserServices;

    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity entry)
    {
        UserServices.addEntry(entry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
