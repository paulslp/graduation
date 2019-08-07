package ru.javawebinar.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.model.Menu;
import ru.javawebinar.graduation.service.MenuService;
import ru.javawebinar.graduation.util.DateTimeUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.graduation.util.Util.orElse;
import static ru.javawebinar.graduation.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(MenuRestController.REST_URL)
public class MenuRestController {
    static final String REST_URL = "/rest";

    @Autowired
    private final MenuService service;

    public MenuRestController(MenuService service) {
        this.service = service;
    }


    @PostMapping(value = "/admin/menu", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu) {
        Menu created = service.create(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL)
                    .buildAndExpand(created).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/admin/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable("id") int id) {
        assureIdConsistent(menu, id);
        service.update(menu);
    }

    @DeleteMapping(value = "/admin/menu/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping(value = "/admin/menu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id) {
        return service.get(id);
    }


    @GetMapping(value = "user/menu/filter")
    public List<Menu> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getBetween(orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
    }

    @GetMapping(value = "user/menu/filter/{restaurantId}")
    public List<Menu> getBetweenForRestaurant(@PathVariable("restaurantId") int restaurantId,
                                              @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                              @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getBetweenForRestaurant(restaurantId, orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
    }

}
