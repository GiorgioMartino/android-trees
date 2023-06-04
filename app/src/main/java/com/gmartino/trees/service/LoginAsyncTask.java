package com.gmartino.trees.service;

import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE_EMAIL;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE_PASSWORD;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gmartino.trees.entity.User;

import java.lang.ref.WeakReference;

public class LoginAsyncTask extends AsyncTask<Void, Void, User> {

    private static final String LOG_TAG = LoginAsyncTask.class.getSimpleName();

    private WeakReference<Button> button;
    private UserViewModel userViewModel;

    private SharedPreferences sharedPreferences;

    public LoginAsyncTask(Button b, UserViewModel userViewModel,
                          SharedPreferences sharedPreferences) {
        this.button = new WeakReference<>(b);
        this.userViewModel = userViewModel;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    protected User doInBackground(Void... voids) {
        Log.d(LOG_TAG, "AsyncTask Started");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        String email = sharedPreferences.getString(USER_SHARED_PREFERENCE_EMAIL, "");
        String password = sharedPreferences.getString(USER_SHARED_PREFERENCE_PASSWORD, "");

        Log.d(LOG_TAG, String.format("Found Email: %s and Password in SharedPreferences", email));

        return userViewModel.findUser(email, password);
    }

    protected void onPostExecute(User user) {
        if (user == null) {
            Log.d(LOG_TAG, "User not found");
            return;
        }
        Log.d(LOG_TAG, "AsyncTask found user " + user.getEmail());

        button.get().setVisibility(View.VISIBLE);
        button.get().setText(String.format("Login as %s?", user.getUsername()));

        userViewModel.setUserSavedCredentials(user);
    }
}
