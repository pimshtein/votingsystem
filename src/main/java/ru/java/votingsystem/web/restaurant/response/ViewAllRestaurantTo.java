package ru.java.votingsystem.web.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewAllRestaurantTo {
    private Integer id;

    private String name;
}
