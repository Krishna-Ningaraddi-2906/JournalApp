package com.springbootProjects.JournalApp.Entity.UserEntity;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class UserEntity
{
    @Id
    private ObjectId id;
    // here we are indexing that username should be unique for this to work we need to add the line in application.properties
    @Indexed(unique = true)
    @NonNull                // UserName can't be null
    private String userName;
    @NonNull                // password can't be null
    private String password;

    @DBRef // this annotation helps to refer to the
    private List<JournalEntity> journalEntries=new ArrayList<>();
    private List<String> role;
}
