package ru.java.votingsystem.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.java.votingsystem.model.AuthUser;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.repository.VoteRepository;
import ru.java.votingsystem.usecase.VoteForRestaurant;
import ru.java.votingsystem.web.vote.request.CreateVoteTo;
import ru.java.votingsystem.web.vote.response.AllVotesByRestaurantTo;
import ru.java.votingsystem.web.vote.response.ResponseMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    static public final String REST_URL = "/api/v1/votes/";

    private final VoteRepository repository;
    private final VoteForRestaurant useCase;

    @GetMapping("by-restaurant/")
    @Operation(description = "Get all votes sorted by votes")
    public List<AllVotesByRestaurantTo> getAll() {
        log.info("getAll");

        return ResponseMapper.mapCountVotes(repository.countVotesByRestaurantPerDayNative());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(
            @AuthenticationPrincipal AuthUser authUser,
            @Valid @RequestBody CreateVoteTo voteTo
    ) {
        User user = authUser.user();
        log.info("create {} for user {}", voteTo, user.id());
        useCase.execute(voteTo, user);
    }
}