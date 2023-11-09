package com.dostiev.web.service;

import com.dostiev.web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    void delete(User user);

    void update(User user);

    User getById(int id);
}
