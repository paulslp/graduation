package ru.javawebinar.graduation.repository;

import ru.javawebinar.graduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    boolean delete(int id);

    Menu get(int id);

    List<Menu> getBetween(LocalDate startDate, LocalDate endDate);

    List<Menu> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate);
}