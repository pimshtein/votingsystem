package ru.java.votingsystem.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
}