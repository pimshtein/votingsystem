package ru.java.votingsystem.web.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ViewRestaurantTo {
    private Integer id;

    private String name;

    private List<ViewMenuTo> menus;
}
