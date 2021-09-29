package ru.java.votingsystem.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.error.CantVotingBecauseTimeIsOverException;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class VoteForRestaurant {
    private static final int UPPER_LIMIT_HOUR_FOR_VOTING = 11;
    private static final int UPPER_LIMIT_MINUTE_FOR_VOTING = 0;

    private final VoteRepository repository;

    @Transactional
    public Vote execute(Vote vote, int userId) {
        LocalDateTime now = LocalDateTime.now();

        Vote voteByUserPerDay = repository.getByUserAndCreated(userId);
        if (voteByUserPerDay.getId() == null) {
            return repository.save(vote);
        }

        LocalDateTime upperVotingTime = now.withHour(UPPER_LIMIT_HOUR_FOR_VOTING)
                .withMinute(UPPER_LIMIT_MINUTE_FOR_VOTING)
                .withSecond(0)
                .truncatedTo(ChronoUnit.SECONDS);
        if (now.isAfter(upperVotingTime)) {
            throw new CantVotingBecauseTimeIsOverException();
        }

        voteByUserPerDay.setRestaurant(vote.getRestaurant());
        voteByUserPerDay.setCreated(now);
        return repository.save(voteByUserPerDay);
    }
}
