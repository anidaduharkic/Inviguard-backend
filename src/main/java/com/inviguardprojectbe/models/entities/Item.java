package com.inviguardprojectbe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "items")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Item {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String itemName;

    @Column(name = "inStock")
    private int leftInStock;


}
