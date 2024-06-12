package com.inviguardprojectbe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@Validated

public class ItemDto {

    private long id;

    private String itemName;

    private Integer leftInStock;
}
