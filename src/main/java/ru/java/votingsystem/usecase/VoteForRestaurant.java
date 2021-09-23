package ru.java.votingsystem.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.error.CantVotingBecauseTimeIsOverException;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.model.VoteByUserPerDay;
import ru.java.votingsystem.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class VoteForRestaurant {
    private static final int UPPER_LIMIT_TIME_FOR_VOTING = 11;

    private final VoteRepository repository;

    @Transactional
    public Vote execute(Vote vote, int userId) {
        LocalDateTime now = LocalDateTime.now();

        Vote voteByUserPerDay = repository.getByUserAndCreated(userId);
        if (voteByUserPerDay.getId() == null) {
            return repository.save(vote);
        }

        LocalDateTime upperVotingTime = LocalDateTime.now().withHour(UPPER_LIMIT_TIME_FOR_VOTING);
        if (now.isBefore(upperVotingTime)) {
            throw new CantVotingBecauseTimeIsOverException();
        }

        repository.delete(voteByUserPerDay.getId());
        voteByUserPerDay.setRestaurant(vote.getRestaurant());
        Vote newVote = repository.save(voteByUserPerDay);
        return newVote;
    }
}
