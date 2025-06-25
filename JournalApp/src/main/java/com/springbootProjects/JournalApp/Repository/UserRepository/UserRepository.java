package com.springbootProjects.JournalApp.Repository.UserRepository;

import com.springbootProjects.JournalApp.Entity.UserEntity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId>
{
    // Spring boot offer the feature where we can make customer method just we need to mention as below as per requirement
    // Here we need to find the user details by userName so we need to mention it as findByUserName(String userName)

    //findBy- need to be used in general followed by the variable name used in entity class and in the parenthesis need to
    // mention the type of the variable mentioned in entity class and the variable name

    UserEntity findByUserName(String userName);
    UserEntity deleteByUserName(String userName);


}
