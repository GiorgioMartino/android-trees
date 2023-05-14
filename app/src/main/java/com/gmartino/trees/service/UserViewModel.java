package com.gmartino.trees.service;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gmartino.trees.repository.UserRepository;
import com.gmartino.trees.entity.User;

public class UserViewModel extends AndroidViewModel {

    private static final String LOG_TAG = UserViewModel.class.getSimpleName();

    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public User saveUser(String username, String email, String password) {
        Log.d(LOG_TAG, String.format("Saving User %s", email));

        User user = new User(username, email, password);
        userRepository.insert(user);
        return user;
    }

    public boolean checkUserExists(String email, String password) {
        return findUser(email, password) != null;
    }

    public User findUser(String email, String password) {
        Log.d(LOG_TAG, String.format("Searching User %s", email));
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
