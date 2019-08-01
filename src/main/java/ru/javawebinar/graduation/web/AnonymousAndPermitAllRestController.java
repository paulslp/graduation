package ru.javawebinar.graduation.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.model.User;
import ru.javawebinar.graduation.service.UserService;
import ru.javawebinar.graduation.to.UserTo;
import ru.javawebinar.graduation.util.UserUtil;

import javax.validation.Valid;
import java.net.URI;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(AnonymousAndPermitAllRestController.REST_URL)
public class AnonymousAndPermitAllRestController {
    static final String REST_URL = "/rest";

    @Autowired
    private UserService userService;

    @PostMapping(value = "/profile/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        User user = UserUtil.createNewFromTo(userTo);
        checkNew(user);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/all/welcome")
    public String welcome(ModelMap model) {
        return "<h1>Welcome to Graduation Project</h1>";
    }

}

