package ru.java.votingsystem.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.repository.MenuRepository;
import ru.java.votingsystem.repository.RestaurantRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static ru.java.votingsystem.util.validation.ValidationUtil.assureIdConsistent;
import static ru.java.votingsystem.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
final public class RestaurantController {

    static final String REST_URL = "/api/v1/restaurants/";

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("{id}/")
    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(restaurantRepository.findById(id));
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantRepository.deleteExisted(id);
    }

    @GetMapping
    @Operation(description = "Get all restaurants")
    public List<Restaurant> getAll() {
        log.info("getAll");
         return restaurantRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant created = prepareAndSave(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}/")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        prepareAndUpdate(restaurant);
    }

    private void prepareAndUpdate(Restaurant restaurant) {
        if (!restaurant.getMenus().isEmpty()) {
            List<Menu> menus = new ArrayList<>(restaurant.getMenus());
            restaurant.getMenus().clear();
            restaurantRepository.save(restaurant);
            restaurant.setMenus(menus);
        }
        restaurantRepository.save(restaurant);
    }

    private Restaurant prepareAndSave(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}