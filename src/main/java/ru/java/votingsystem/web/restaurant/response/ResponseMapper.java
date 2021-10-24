package ru.java.votingsystem.web.restaurant.response;

import ru.java.votingsystem.model.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapper {
    public static ViewRestaurantTo mapViewRestaurantTo(Restaurant restaurant) {
        List<ViewMenuTo> viewMenuTos = restaurant.getMenus().stream()
                .map(menu -> new ViewMenuTo(menu.getId(), menu.getDishName(), menu.getPrice()))
                .collect(Collectors.toList());

        return new ViewRestaurantTo(restaurant.getId(), restaurant.getName(), viewMenuTos);
    }

    public static List<ViewAllRestaurantTo> mapViewAllRestaurantTos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> new ViewAllRestaurantTo(restaurant.getId(), restaurant.getName()))
                .collect(Collectors.toList());
    }
}
