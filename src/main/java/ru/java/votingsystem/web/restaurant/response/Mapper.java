package ru.java.votingsystem.web.restaurant.response;

import ru.java.votingsystem.model.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static ViewRestaurantTo map(Restaurant restaurant) {
        List<ViewMenuTo> viewMenuTos = restaurant.getMenus().stream()
                .map(menu -> new ViewMenuTo(menu.getId(), menu.getDishName(), menu.getPrice()))
                .collect(Collectors.toList());

        return new ViewRestaurantTo(restaurant.getId(), restaurant.getName(), viewMenuTos);
    }
}
