package com.springbootProjects.JournalApp.Repository.UserRepository;

import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId>
{
    UserEntity findByuserName(String userName);

}
