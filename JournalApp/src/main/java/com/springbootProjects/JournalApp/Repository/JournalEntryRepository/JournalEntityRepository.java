package com.springbootProjects.JournalApp.Repository.JournalEntryRepository;

import com.springbootProjects.JournalApp.Entity.JournalEntity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntityRepository extends MongoRepository<JournalEntity, ObjectId>
{

}
