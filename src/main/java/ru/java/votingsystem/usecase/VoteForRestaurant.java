package ru.java.votingsystem.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.error.CantVotingBecauseTimeIsOverException;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.repository.VoteRepository;
import ru.java.votingsystem.web.vote.request.CreateVoteTo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class VoteForRestaurant {
    private static final int UPPER_LIMIT_HOUR_FOR_VOTING = 11;
    private static final int UPPER_LIMIT_MINUTE_FOR_VOTING = 0;
    private static final int UPPER_LIMIT_SECOND_FOR_VOTING = 0;

    private final VoteRepository repository;

    @Transactional
    public void execute(CreateVoteTo voteTo, int userId) {
        Vote voteByUserPerDay = repository.getByUserAndCreated(userId);
        if (voteByUserPerDay.getId() == null) {
            Vote vote = new Vote(voteTo.getRestaurantId());
            repository.save(vote);
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime upperVotingTime = now.withHour(UPPER_LIMIT_HOUR_FOR_VOTING)
                .withMinute(UPPER_LIMIT_MINUTE_FOR_VOTING)
                .withSecond(UPPER_LIMIT_SECOND_FOR_VOTING)
                .truncatedTo(ChronoUnit.SECONDS);
        if (now.isAfter(upperVotingTime)) {
            throw new CantVotingBecauseTimeIsOverException();
        }

        voteByUserPerDay.setRestaurantId(voteTo.getRestaurantId());
        voteByUserPerDay.setCreated(now);
        repository.save(voteByUserPerDay);
    }
}
