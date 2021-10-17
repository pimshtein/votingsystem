package ru.java.votingsystem.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.java.votingsystem.model.Menu;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.repository.RestaurantRepository;
import ru.java.votingsystem.web.restaurant.response.Mapper;
import ru.java.votingsystem.web.restaurant.response.ViewAllRestaurantTo;
import ru.java.votingsystem.web.restaurant.response.ViewRestaurantTo;
import ru.java.votingsystem.web.restaurant.response.create.CreateRestaurantTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ViewRestaurantTo> get(int id) {
        log.info("get {}", id);

        Optional<Restaurant> restaurant = restaurantRepository.findWithMenus(id);
        if (restaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Mapper.mapViewRestaurantTo(restaurant.get()));
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantRepository.deleteExisted(id);
    }

    @GetMapping(params = {"page", "size"})
    @Operation(description = "Get all restaurants")
    public Page<ViewAllRestaurantTo> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("getAll");
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
        return new PageImpl<>(
            Mapper.mapViewAllRestaurantTos(restaurantPage.getContent()),
            restaurantPage.getPageable(),
            restaurantPage.getTotalElements()
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody CreateRestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        Restaurant restaurant = Mapper.createRestaurantFromTo(restaurantTo);
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
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
}