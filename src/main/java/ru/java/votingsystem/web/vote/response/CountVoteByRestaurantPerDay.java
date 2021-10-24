package ru.java.votingsystem.web.vote.response;

import java.time.LocalDate;

public interface CountVoteByRestaurantPerDay {
    int getRestaurantId();

    int getCount();

    LocalDate getVoteDate();
}
