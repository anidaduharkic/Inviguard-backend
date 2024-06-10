package com.inviguardprojectbe.models;

import com.inviguardprojectbe.models.entities.Item;
import com.inviguardprojectbe.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Validated

public class OrderDto {

    private Long id;

    private User user;

    private Item item;

    private Integer numberOrdered;

    private LocalDateTime orderDate;

}
