package com.gmartino.trees.dao;

import android.app.Application;
import android.util.Log;

import com.gmartino.trees.SignUpActivity;
import com.gmartino.trees.entity.User;

import java.util.concurrent.ExecutionException;

public class UserRepository {

    private static final String LOG_TAG = UserRepository.class.getSimpleName();

    private UserDao userDao;

    public UserRepository(Application application) {
        TreeDatabase db = TreeDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public void insert(User user) {
        TreeDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public User findByMail(String email) {
        try {
            return TreeDatabase.databaseWriteExecutor.submit(() -> userDao.findByMail(email)).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d(LOG_TAG, "Cannot find user with mail: " + email);
            return null;
        }
    }

}
