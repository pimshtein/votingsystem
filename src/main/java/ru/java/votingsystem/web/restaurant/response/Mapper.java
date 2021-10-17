package ru.java.votingsystem.web.restaurant.response;

import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.web.restaurant.response.create.CreateRestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
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

    public static Restaurant createRestaurantFromTo(CreateRestaurantTo restaurantTo) {
        Restaurant restaurant = new Restaurant(restaurantTo.getName());
        List<Menu> menus = restaurantTo.getMenu().stream()
                .map(createMenuTo -> {
                        Menu menu = new Menu(createMenuTo.getDishName(), createMenuTo.getPrice());
                        menu.setRestaurant(restaurant);
                        return menu;
                })
                .collect(Collectors.toList());
        restaurant.setMenus(menus);

        return restaurant;
    }
}
