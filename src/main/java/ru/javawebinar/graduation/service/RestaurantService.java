package ru.javawebinar.graduation.service;

import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAll();

    Restaurant get(int id) throws NotFoundException;

    Restaurant create(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    Restaurant getByName(String name) throws NotFoundException;

    List<Restaurant> getRestaurantListWithMenuOnDate(LocalDate startDate, LocalDate endDate);
}
