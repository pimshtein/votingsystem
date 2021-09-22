package ru.java.votingsystem.util;

import lombok.experimental.UtilityClass;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RestaurantUtil {

    public static Restaurant prepareToSave(Restaurant restaurant) {
        List<Menu> menus = restaurant.getMenus();
        restaurant.setMenus(new ArrayList<>());
        restaurant.setMenus(menus);
        return restaurant;
    }
}