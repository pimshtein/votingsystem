package ru.java.votingsystem.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.java.votingsystem.model.AuthUser;
import ru.java.votingsystem.model.CountVoteByRestaurantPerDay;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.repository.MenuRepository;
import ru.java.votingsystem.repository.VoteRepository;
import ru.java.votingsystem.usecase.VoteForRestaurant;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.java.votingsystem.util.validation.ValidationUtil.assureIdConsistent;
import static ru.java.votingsystem.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    static final String REST_URL = "/api/v1/votes/";

    private final VoteRepository repository;
    private final VoteForRestaurant useCase;

    @GetMapping("by-restaurant/")
    @Operation(description = "Get all votes sorted by votes")
    public List<CountVoteByRestaurantPerDay> getAll() {
        log.info("getAll");
        return repository.countVotesByRestaurantPerDayNative();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Vote vote) {
        int userId = authUser.id();
        log.info("create {} for user {}", vote, userId);
        checkNew(vote);
        Vote created = useCase.execute(vote, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}/")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}