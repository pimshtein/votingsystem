package ru.java.votingsystem.web.restaurant.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRestaurantTo {
    private String name;

    private List<UpdateMenuTo> menu;
}
