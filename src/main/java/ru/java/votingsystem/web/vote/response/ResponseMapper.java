package ru.java.votingsystem.web.vote.response;

import ru.java.votingsystem.model.CountVoteByRestaurantPerDay;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapper {
    public static List<AllVotesByRestaurantTo> mapCountVotes(List<CountVoteByRestaurantPerDay> countVotes) {
        return countVotes.stream()
                .map(vote -> new AllVotesByRestaurantTo(vote.getRestaurantId(), vote.getVoteDate(), vote.getCount()))
                .collect(Collectors.toList());
    }
}
