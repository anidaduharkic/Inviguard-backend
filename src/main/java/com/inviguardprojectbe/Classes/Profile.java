package com.inviguardprojectbe.Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Profile {

    private long id;
    private String name;
    private String surname;
    private String about;
}
