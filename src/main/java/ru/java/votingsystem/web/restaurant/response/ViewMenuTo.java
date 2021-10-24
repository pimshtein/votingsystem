package ru.java.votingsystem.web.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewMenuTo {
    private Integer id;

    private String dishName;

    private Integer price;
}
