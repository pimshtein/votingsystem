package ru.java.votingsystem.web;

import ru.java.votingsystem.MatcherFactory;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;

import java.util.List;

public class TestData {
    public static final MatcherFactory.Matcher<Restaurant> MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final String ADMIN_EMAIL = "admin@mail.com";
    public static final Integer ADMIN_ID = 2;
    public static final Integer FIRST_RESTAURANT_ID = 1;
    public static final Integer SECOND_RESTAURANT_ID = 2;

    public static List<Menu> menus = List.of(
            new Menu(1, "breakfast", 10),
            new Menu(2, "launch", 20),
            new Menu(3, "dinner", 30)
    );
    public static Restaurant restaurant = new Restaurant(1, "First", menus);
}
