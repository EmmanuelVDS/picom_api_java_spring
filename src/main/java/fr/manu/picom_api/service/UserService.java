package fr.manu.picom_api.service;

import fr.manu.picom_api.model.User;

import java.util.List;

public interface UserService {

    User findByUserEmail(String email);

    User addUser(User user);

    List<User> getUsers();
}
