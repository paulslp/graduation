package ru.javawebinar.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.graduation.model.Menu;
import ru.javawebinar.graduation.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
class DataJpaMenuRepositoryImpl implements MenuRepository {

    @Autowired
    private CrudMenuRepository crudRepository;

    @Override
    public Menu save(Menu user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudRepository.get(id);
    }

    @Override
    public List<Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        return crudRepository.getBetween(startDate, endDate);
    }

    @Override
    public List<Menu> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate) {
        return crudRepository.getBetweenForRestaurant(restaurantId, startDate, endDate);
    }
}
