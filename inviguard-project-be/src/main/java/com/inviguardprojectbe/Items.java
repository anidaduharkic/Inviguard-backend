package com.inviguardprojectbe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Items {

    private long id;
    private String itemName;
    private int leftInStock;
}
