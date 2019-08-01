package ru.javawebinar.graduation.service;

import ru.javawebinar.graduation.model.Menu;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu create(Menu menu);

    void delete(int id) throws NotFoundException;

    void update(Menu menu);

    Menu get(int id) throws NotFoundException;

    List<Menu> getBetween(LocalDate startDate, LocalDate endDate);

    List<Menu> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate);
}