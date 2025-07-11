package com.springbootProjects.JournalApp.Entity.JournalEntity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class JournalEntity
{
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime Date;

}
