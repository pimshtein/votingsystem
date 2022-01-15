package ru.java.votingsystem.web;

import ru.java.votingsystem.MatcherFactory;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.web.restaurant.request.create.CreateMenuTo;
import ru.java.votingsystem.web.restaurant.request.create.CreateRestaurantTo;
import ru.java.votingsystem.web.restaurant.request.update.UpdateMenuTo;
import ru.java.votingsystem.web.restaurant.request.update.UpdateRestaurantTo;
import ru.java.votingsystem.web.vote.response.AllVotesByRestaurantTo;

import java.time.LocalDate;
import java.util.List;

public class TestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);
    public static final MatcherFactory.Matcher<AllVotesByRestaurantTo> ALL_VOTES_BY_RESTAURANT_TO_MATCHER = MatcherFactory.usingIterableElementComparisonStrategy(AllVotesByRestaurantTo.class);

    public static final String ADMIN_EMAIL = "admin@mail.com";
    public static final Integer FIRST_RESTAURANT_ID = 1;
    public static final Integer SECOND_RESTAURANT_ID = 2;
    public static final Integer NEW_MENU_ID = 7;

    public static List<Menu> firstRestaurantMenus = List.of(
            new Menu(1, "breakfast", 10),
            new Menu(2, "launch", 20),
            new Menu(3, "dinner", 30)
    );

    public static Restaurant firstRestaurant = new Restaurant(1, "First", firstRestaurantMenus);

    public static CreateMenuTo newCreateMenuTo = new CreateMenuTo("Test", 10);
    public static CreateRestaurantTo newCreateRestaurantTo = new CreateRestaurantTo("Test", List.of(newCreateMenuTo));

    public static UpdateMenuTo newUpdateMenuTo = new UpdateMenuTo("Test", 10);
    public static UpdateRestaurantTo newUpdateRestaurantTo = new UpdateRestaurantTo("Test", List.of(newUpdateMenuTo));

    public static List<AllVotesByRestaurantTo> allVotes = List.of(
            new AllVotesByRestaurantTo(FIRST_RESTAURANT_ID, LocalDate.now(), 2),
            new AllVotesByRestaurantTo(SECOND_RESTAURANT_ID, LocalDate.now(), 1)
    );
}
