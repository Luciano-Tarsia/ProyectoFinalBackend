package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("Users")
public class User {

    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String password2;

    User(){};

    User(String name, String phoneNumber, String email, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

}
