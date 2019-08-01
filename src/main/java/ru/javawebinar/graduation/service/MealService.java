package ru.javawebinar.graduation.service;


import ru.javawebinar.graduation.model.Meal;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    Meal getByName(String name) throws NotFoundException;

    void update(Meal meal);

    List<Meal> getAll();

}