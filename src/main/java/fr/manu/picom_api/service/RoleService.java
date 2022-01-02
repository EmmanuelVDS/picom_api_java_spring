package fr.manu.picom_api.service;

import fr.manu.picom_api.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);

    void addRoleToUser(String username, String rolename);

    List<Role> getRoles();
}
