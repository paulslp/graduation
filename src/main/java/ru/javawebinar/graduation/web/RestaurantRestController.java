package ru.javawebinar.graduation.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.model.Meal;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.service.MealService;
import ru.javawebinar.graduation.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {
    static final String REST_URL = "/rest";

    @Autowired
    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }


    @GetMapping(value = "/admin/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/admin/restaurant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(value = "/admin/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/admin/restaurant/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/admin/restaurant/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    @GetMapping(value = "/admin/restaurant/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getByName(@RequestParam("name") String name) {
        return service.getByName(name);
    }

    @GetMapping(value = "/user/restaurant/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurantListWithMenuOnDate(@RequestParam(value = "startDate") LocalDate startDate,
                                                            @RequestParam(value = "endDate") LocalDate endDate) {
        return service.getRestaurantListWithMenuOnDate(startDate, endDate);
    }


}
