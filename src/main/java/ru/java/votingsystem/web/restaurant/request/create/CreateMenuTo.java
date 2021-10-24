package ru.java.votingsystem.web.restaurant.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateMenuTo {
    private String dishName;

    private Integer price;
}
