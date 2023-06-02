package com.gmartino.trees;

import static com.gmartino.trees.HomeActivity.EXTRA_USER;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gmartino.trees.entity.User;
import com.gmartino.trees.service.UserFactory;
import com.gmartino.trees.service.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MAIL = "com.gmartino.trees.extra.MAIL";

    private EditText mailEditText;
    private EditText passwordEditText;

    private TextView errorTextView;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailEditText = findViewById(R.id.mainEmailText);
        passwordEditText = findViewById(R.id.mainPasswordText);
        errorTextView = findViewById(R.id.mainErrorLabel);
    }

    public void logIn(View view) {
        Log.d(LOG_TAG, "LOGIN Button clicked!");

        errorTextView.setVisibility(View.INVISIBLE);

        String mail = mailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        User user = userViewModel.findUser(mail, password);

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

    public void registerUser(View view) {
        Log.d(LOG_TAG, "REGISTER Button clicked!");

        Intent intent = new Intent(this, SignUpActivity.class);
        String mail = mailEditText.getText().toString();
        intent.putExtra(EXTRA_MAIL, mail);
        startActivity(intent);
    }
}