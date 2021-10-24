package ru.java.votingsystem.web.restaurant.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMenuTo {
    private String dishName;

    private Integer price;
}
