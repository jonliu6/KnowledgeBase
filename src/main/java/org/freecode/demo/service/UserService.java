package org.freecode.demo.service;

import org.freecode.demo.model.User;
import org.freecode.demo.model.UserDetailsImpl;
import org.freecode.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository aUserRepo) {
        this.userRepo = aUserRepo;
    }

    public User findByToken(String token) {
        return userRepo.findById(token).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return new UserDetailsImpl(user);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }
}
