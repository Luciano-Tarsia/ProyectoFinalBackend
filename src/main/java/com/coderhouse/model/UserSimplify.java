package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSimplify {

    private String name;
    private String password;

    UserSimplify(){};

    UserSimplify(String name, String password){
        this.name = name;
        this.password = password;
    }

}
