package ru.java.votingsystem.web.restaurant.request;

import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.web.restaurant.request.create.CreateRestaurantTo;
import ru.java.votingsystem.web.restaurant.request.update.UpdateRestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper {

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

    public static Restaurant createRestaurantFromUpdateTo(UpdateRestaurantTo restaurantTo) {
        Restaurant restaurant = new Restaurant(restaurantTo.getName());
        List<Menu> menus = restaurantTo.getMenu().stream()
                .map(updateMenuTo -> {
                    Menu menu = new Menu(updateMenuTo.getDishName(), updateMenuTo.getPrice());
                    menu.setRestaurant(restaurant);
                    return menu;
                })
                .collect(Collectors.toList());
        restaurant.setMenus(menus);

        return restaurant;
    }
}
