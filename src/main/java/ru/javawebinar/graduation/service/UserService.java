package ru.javawebinar.graduation.service;


import ru.javawebinar.graduation.model.User;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    void enable(int id, boolean enable);

}