package ru.java.votingsystem.repository;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Menu;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
    List<Menu> findAllByRestaurantId(int restaurant);
}