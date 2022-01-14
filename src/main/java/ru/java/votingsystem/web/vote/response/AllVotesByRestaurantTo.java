package ru.java.votingsystem.web.vote.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AllVotesByRestaurantTo {
    private Integer restaurantId;

    private LocalDate voteDate;

    private Integer count;
}
