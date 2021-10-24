package ru.java.votingsystem.web.restaurant.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateRestaurantTo {
    private String name;

    private List<CreateMenuTo> menu;
}
