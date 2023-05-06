package com.gmartino.trees.service;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gmartino.trees.dao.UserRepository;
import com.gmartino.trees.entity.User;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

        public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public User saveUser(String username, String email, String password) {
        User user = new User(username, email, password);
        userRepository.insert(user);
        return user;
    }

    public boolean checkUserExists(String email, String password) {
        return findUser(email, password) != null;
    }

    public User findUser(String email, String password) {
        User user = userRepository.findByMail(email);
        if (user != null && user.getPassword().equals(password))
            return user;
        else
            return null;
    }

    public boolean checkEmailExist(String mail) {
        return userRepository.findByMail(mail) != null;
    }
}
