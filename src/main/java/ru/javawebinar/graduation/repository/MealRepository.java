package ru.javawebinar.graduation.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Meal;

import java.util.List;

@Transactional
public interface MealRepository {

    Meal save(Meal meal);

    boolean delete(int id);

    Meal get(int id);

    Meal getByName(String name);

    List<Meal> getAll();
}
