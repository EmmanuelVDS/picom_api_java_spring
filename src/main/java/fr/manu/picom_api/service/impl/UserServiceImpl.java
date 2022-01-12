package fr.manu.picom_api.service.impl;

import fr.manu.picom_api.dao.UserDao;
import fr.manu.picom_api.model.User;
import fr.manu.picom_api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUserEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User addUser(User user) {
        log.info("Saving new user {} to db", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);

        if (user == null) {
            log.error("user not found in the db");
            throw new UsernameNotFoundException("user not found in the db");
        } else {
            log.info("User found in the db : " + user);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
