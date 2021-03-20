package com.challenge.techbase.services.impl;

import com.challenge.techbase.mappers.UserRepository;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepo;

    @Override
    public User login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);

        if (!userOptional.isPresent()){
            logger.error(
                    messageSource.getMessage("email.notfound",new Object[] {email}, Locale.getDefault()));
            return null;
        }

        User user = userOptional.get();
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        return this.userRepo.saveAndFlush(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return this.userRepo.findById(id);
    }
}
