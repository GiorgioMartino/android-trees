package com.gmartino.trees;

import static com.gmartino.trees.HomeActivity.EXTRA_USER;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE_EMAIL;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE_PASSWORD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gmartino.trees.entity.User;
import com.gmartino.trees.service.UserFactory;
import com.gmartino.trees.service.UserViewModel;

public class SignUpActivity extends AppCompatActivity {

    private static final String LOG_TAG = SignUpActivity.class.getSimpleName();

    private static final String NAME_TEXT_STATE = "nameTextState";
    private static final String EMAIL_TEXT_STATE = "emailTextState";
    private static final String PASSWORD_TEXT_STATE = "passwordTextState";
    private static final String ERROR_VISIBILITY_STATE = "errorVisibilityState";

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView errorTextView;

    private UserViewModel userViewModel;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.signupNameText);
        emailEditText = findViewById(R.id.signupEmailText);
        passwordEditText = findViewById(R.id.signupPasswordText);
        errorTextView = findViewById(R.id.signupErrorLabel);

        if (savedInstanceState != null) {
            nameEditText.setText(savedInstanceState.getString(NAME_TEXT_STATE));
            emailEditText.setText(savedInstanceState.getString(EMAIL_TEXT_STATE));
            passwordEditText.setText(savedInstanceState.getString(PASSWORD_TEXT_STATE));
            errorTextView.setVisibility(
                    savedInstanceState.getInt(ERROR_VISIBILITY_STATE));
        }

        Intent intent = getIntent();
        String email = intent.getStringExtra(MainActivity.EXTRA_MAIL);
        if (email != null)
            emailEditText.setText(email);

        sharedPreferences = getSharedPreferences(USER_SHARED_PREFERENCE, MODE_PRIVATE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NAME_TEXT_STATE, nameEditText.getText().toString());
        outState.putString(EMAIL_TEXT_STATE, emailEditText.getText().toString());
        outState.putString(PASSWORD_TEXT_STATE, passwordEditText.getText().toString());
        outState.putInt(ERROR_VISIBILITY_STATE, errorTextView.getVisibility());
    }

    public void confirmRegister(View view) {
        Log.d(LOG_TAG, "REGISTER Button clicked!");
        errorTextView.setVisibility(View.INVISIBLE);

        boolean emailExist = userViewModel.checkEmailExist(emailEditText.getText().toString());

        if (emailExist) {
            Log.d(LOG_TAG, "Error during SIGNUP");
            errorTextView.setVisibility(View.VISIBLE);
        } else {
            User user = userViewModel.saveUser(nameEditText.getText().toString(),
                    emailEditText.getText().toString(), passwordEditText.getText().toString());

            Log.d(LOG_TAG, "New User registered " + user.getEmail());

            Log.d(LOG_TAG, "Saving login info in SharedPreferences");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USER_SHARED_PREFERENCE_EMAIL, emailEditText.getText().toString());
            editor.putString(USER_SHARED_PREFERENCE_PASSWORD,
                    passwordEditText.getText().toString());
            editor.apply();

            // go to home
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(EXTRA_USER, UserFactory.userToUserDTO(user));
            startActivity(intent);
        }
    }
}