package ru.java.votingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.web.vote.response.CountVoteByRestaurantPerDay;

import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query(value = "SELECT v.restaurant_id AS restaurantId, DATE(v.created) AS voteDate, COUNT(v.restaurant_id) AS count "
            + "FROM votes AS v WHERE DATE(created) = CURRENT_DATE GROUP BY restaurantId, voteDate "
            + "ORDER BY voteDate, count DESC", nativeQuery = true)
    List<CountVoteByRestaurantPerDay> countVotesByRestaurantPerDayNative();

    @Query(value = "SELECT v.* FROM votes AS v "
            + "WHERE v.user_id = :userId AND DATE(created) = CURRENT_DATE LIMIT 1", nativeQuery = true)
    Vote getByUserAndCreated(@Param("userId") int userId);
}