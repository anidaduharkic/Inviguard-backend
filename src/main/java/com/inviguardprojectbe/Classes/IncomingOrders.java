package com.inviguardprojectbe.Classes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "orders")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class IncomingOrders {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "itemOrdered")
    private String itemOrdered;

    @Column(name = "capacityOrdered")
    private int numberOrdered;

    @Column(name = "orderedBy")
    private String orderedBy;

}
