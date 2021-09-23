package ru.java.votingsystem.model;

import java.util.Date;

public interface CountVoteByRestaurantPerDay {
    int getRestaurantId();
    int getCount();
    Date getVoteDate();
}
