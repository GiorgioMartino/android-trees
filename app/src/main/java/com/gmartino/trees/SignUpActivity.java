package com.gmartino.trees;

import static com.gmartino.trees.HomeActivity.EXTRA_USER;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE_EMAIL;
import static com.gmartino.trees.MainActivity.USER_SHARED_PREFERENCE_PASSWORD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gmartino.trees.entity.User;
import com.gmartino.trees.service.UserFactory;
import com.gmartino.trees.service.UserViewModel;

public class SignUpActivity extends AppCompatActivity {


    private static final String LOG_TAG = SignUpActivity.class.getSimpleName();

    private EditText nameEditText;
    private EditText mailEditText;
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

        Intent intent = getIntent();
        String mail = intent.getStringExtra(MainActivity.EXTRA_MAIL);

        nameEditText = findViewById(R.id.signupNameText);
        mailEditText = findViewById(R.id.signupEmailText);
        passwordEditText = findViewById(R.id.signupPasswordText);
        errorTextView = findViewById(R.id.signupErrorLabel);

        mailEditText.setText(mail);

        sharedPreferences = getSharedPreferences(USER_SHARED_PREFERENCE, MODE_PRIVATE);
    }

    public void confirmRegister(View view) {
        Log.d(LOG_TAG, "REGISTER Button clicked!");
        errorTextView.setVisibility(View.INVISIBLE);

        boolean emailExist = userViewModel.checkEmailExist(mailEditText.getText().toString());

        if (emailExist) {
            Log.d(LOG_TAG, "Error during SIGNUP");
            errorTextView.setVisibility(View.VISIBLE);
        } else {
            User user = userViewModel.saveUser(nameEditText.getText().toString(),
                    mailEditText.getText().toString(), passwordEditText.getText().toString());

            Log.d(LOG_TAG, "New User registered " + user.getEmail());

            Log.d(LOG_TAG, "Saving login info in SharedPreferences");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USER_SHARED_PREFERENCE_EMAIL, mailEditText.getText().toString());
            editor.putString(USER_SHARED_PREFERENCE_PASSWORD, passwordEditText.getText().toString());
            editor.apply();

            // go to home
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(EXTRA_USER, UserFactory.userToUserDTO(user));
            startActivity(intent);
        }
    }
}