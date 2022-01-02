package fr.manu.picom_api.service.impl;

import fr.manu.picom_api.dao.RoleDao;
import fr.manu.picom_api.dao.UserDao;
import fr.manu.picom_api.model.Role;
import fr.manu.picom_api.model.User;
import fr.manu.picom_api.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final UserDao userDao;

    @Override
    public void addRole(Role role) {
        log.info("Saving new role {} to db", role.getName());
        roleDao.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to user {}", rolename, username);
        User user = userDao.findByEmail(username);
        Role role = roleDao.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleDao.findAll();
    }
}
