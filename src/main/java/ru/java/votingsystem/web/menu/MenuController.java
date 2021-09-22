package ru.java.votingsystem.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.repository.MenuRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.java.votingsystem.util.validation.ValidationUtil.assureIdConsistent;
import static ru.java.votingsystem.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MenuController {

    static final String REST_URL = "/api/v1/menus/";

    private final MenuRepository repository;

    public MenuController(MenuRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}/")
    public ResponseEntity<Menu> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @GetMapping("by-restaurant/{restaurantId}/")
    @Operation(description = "Get all menus by restaurant id")
    public List<Menu> getAll(@PathVariable int restaurantId ) {
        log.info("getAll {}", restaurantId);
        return repository.findAllByRestaurantId(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu) {
        log.info("create {}", menu);
        checkNew(menu);
        Menu created = prepareAndSave(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}/")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id) {
        log.info("update {} with id={}", menu, id);
        assureIdConsistent(menu, id);
        prepareAndSave(menu);
    }

    protected Menu prepareAndSave(Menu menu) {
        return repository.save(menu);
    }
}