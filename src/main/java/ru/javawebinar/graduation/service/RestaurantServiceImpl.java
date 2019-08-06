package ru.javawebinar.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;


@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private final RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public List<Restaurant> getRestaurantListWithMenuOnDate(LocalDate startDate, LocalDate endDate) {
        return repository.getRestaurantListWithMenuOnDate(startDate, endDate);
    }

    @Override
    public Restaurant getByName(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByName(email), "name=" + email);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }


}