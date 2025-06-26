package com.ticketingtool.ticketingtool.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {


    @Id
    private String id;

    private String username;
    private String email;
    private String password;

    // Role can be "USER" or "ADMIN"
    private String role;
}


