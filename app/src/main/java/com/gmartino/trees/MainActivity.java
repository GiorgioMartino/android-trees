package com.gmartino.trees;

import static com.gmartino.trees.HomeActivity.EXTRA_USER;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gmartino.trees.entity.User;
import com.gmartino.trees.service.LoginAsyncTask;
import com.gmartino.trees.service.UserFactory;
import com.gmartino.trees.service.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MAIL = "com.gmartino.trees.extra.MAIL";

    public static final String USER_SHARED_PREFERENCE = "userSharedPreference";
    public static final String USER_SHARED_PREFERENCE_EMAIL = "userSharedPreferenceEmail";
    public static final String USER_SHARED_PREFERENCE_PASSWORD = "userSharedPreferencePassword";

    private static final String BUTTON_TEXT_STATE = "buttonTextState";
    private static final String BUTTON_VISIBILITY_STATE = "buttonVisibilityState";
    private static final String EMAIL_TEXT_STATE = "emailTextState";
    private static final String PASSWORD_TEXT_STATE = "passwordTextState";
    private static final String ERROR_VISIBILITY_STATE = "errorVisibilityState";

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView errorTextView;
    private Button loginCredentialsButton;

    private UserViewModel userViewModel;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.mainEmailText);
        passwordEditText = findViewById(R.id.mainPasswordText);
        errorTextView = findViewById(R.id.mainErrorLabel);
        loginCredentialsButton = findViewById(R.id.mainLoginCredentialsButton);

        if (savedInstanceState != null) {
            loginCredentialsButton.setText(savedInstanceState.getString(BUTTON_TEXT_STATE));
            loginCredentialsButton.setVisibility(
                    savedInstanceState.getInt(BUTTON_VISIBILITY_STATE));
            emailEditText.setText(savedInstanceState.getString(EMAIL_TEXT_STATE));
            passwordEditText.setText(savedInstanceState.getString(PASSWORD_TEXT_STATE));
            errorTextView.setVisibility(
                    savedInstanceState.getInt(ERROR_VISIBILITY_STATE));
        }

        sharedPreferences = getSharedPreferences(USER_SHARED_PREFERENCE, MODE_PRIVATE);

        Log.d(LOG_TAG, "Starting AsyncTask");
        new LoginAsyncTask(loginCredentialsButton, userViewModel, sharedPreferences).execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUTTON_TEXT_STATE,
                loginCredentialsButton.getText().toString());
        outState.putInt(BUTTON_VISIBILITY_STATE,
                loginCredentialsButton.getVisibility());
        outState.putString(EMAIL_TEXT_STATE, emailEditText.getText().toString());
        outState.putString(PASSWORD_TEXT_STATE, passwordEditText.getText().toString());
        outState.putInt(ERROR_VISIBILITY_STATE, errorTextView.getVisibility());
    }

    public void logIn(View view) {
        Log.d(LOG_TAG, "LOGIN Button clicked!");

        errorTextView.setVisibility(View.INVISIBLE);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        User user = userViewModel.findUser(email, password);

        if (user != null) {
            Log.d(LOG_TAG, "User Logged In");

            Log.d(LOG_TAG, "Saving login info in SharedPreferences");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USER_SHARED_PREFERENCE_EMAIL, email);
            editor.putString(USER_SHARED_PREFERENCE_PASSWORD, password);
            editor.apply();

            //go to home
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(EXTRA_USER, UserFactory.userToUserDTO(user));
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Error during LOGIN");
            errorTextView.setVisibility(View.VISIBLE);
        }

    }

    public void registerUser(View view) {
        Log.d(LOG_TAG, "REGISTER Button clicked!");

        Intent intent = new Intent(this, SignUpActivity.class);
        String mail = emailEditText.getText().toString();
        intent.putExtra(EXTRA_MAIL, mail);
        startActivity(intent);
    }

    public void loginWithSavedCredentials(View view) {
        User user = userViewModel.getUserSavedCredentials();

        if (user != null) {
            Log.d(LOG_TAG, "User Logged In");

            //go to home
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(EXTRA_USER, UserFactory.userToUserDTO(user));
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Error during LOGIN");
            errorTextView.setVisibility(View.VISIBLE);
        }
    }

}