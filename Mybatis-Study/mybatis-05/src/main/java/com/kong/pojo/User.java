package com.kong.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private int id;
    private String name;
    private String password;

}
