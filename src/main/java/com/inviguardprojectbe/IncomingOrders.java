package com.inviguardprojectbe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class IncomingOrders {

    private long id;
    private String itemOrdered;
    private int numberOrdered;
    private String orderedBy;
}
