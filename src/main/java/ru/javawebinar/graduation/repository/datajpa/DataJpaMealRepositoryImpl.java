package ru.javawebinar.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.graduation.model.Meal;
import ru.javawebinar.graduation.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudMealRepository crudRepository;

    @Override
    public Meal save(Meal meal) {
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Meal getByName(String name) {
        return crudRepository.getByName(name);
    }

    @Override
    public List<Meal> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }


}
