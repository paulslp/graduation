package ru.javawebinar.graduation.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();

    List<Restaurant> getRestaurantListWithMenuOnDate(LocalDate startDate, LocalDate endDate);
}
