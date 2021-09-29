package ru.java.votingsystem.model;

import java.time.LocalDate;

public interface CountVoteByRestaurantPerDay {
    int getRestaurantId();
    int getCount();
    LocalDate getVoteDate();
}
