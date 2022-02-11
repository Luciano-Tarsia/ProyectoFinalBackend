package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserRedis implements Serializable {

    private String id;
    private String password;
    private LocalDateTime time;

    UserRedis(){}

    public UserRedis(String id, String password, LocalDateTime time){
        this.id = id;
        this.password = password;
        this.time = time;
    }

}
