package com.example.tweets.service;

import com.example.tweets.domain.Role;
import com.example.tweets.domain.User;
import com.example.tweets.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @Value("${activationLink}")
    private String activationLink;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public User findById(Long id) {
        return userRepo.getOne(id);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean addUser(User user) {
        User userDb = userRepo.findByUsername(user.getUsername());
        if (userDb != null) {
            return false;
        }
        String passHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passHash);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(false);
        userRepo.save(user);
        sendMessage(user, "Activated", "activate account");
        return true;
    }

    private boolean sendMessage(User user, String subject, String reason) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello %s! Please visit next link: %s/activate/%s for %s",
                    user.getName(), activationLink, user.getActivationCode(), reason
            );
            mailSender.send(user.getEmail(), subject, message);
            return true;
        }
        return false;
    }

    public void updateUser(String name,
                           String username,
                           String email,
                           Map<String, String> form,
                           String active,
                           User user) {
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Enum::toString)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        if (active.equals("true")) {
            user.setActive(true);
        } else {
            user.setActive(false);
        }

        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepo.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return byUsername;
    }

    public boolean isActivated(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public boolean updateProfile(User user, String name, String email, String password) {
        user.setName(name);
        user.setEmail(email);
        if (changePassword(user, password))
            return true;
        userRepo.save(user);
        return false;
    }

    private boolean changePassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(password));
            user.setActive(false);
            if (sendMessage(user, "Change password", "change password")) {
                userRepo.save(user);
                return true;
            }
        }
        return false;
    }
}