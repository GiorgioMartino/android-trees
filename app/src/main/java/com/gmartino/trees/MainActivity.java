package com.gmartino.trees;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gmartino.trees.service.UserViewModel;
import com.gmartino.trees.entity.User;

public class MainActivity extends AppCompatActivity {

//    UserService userService = new UserService();

//    Database database = Database.getInstance();

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MAIL = "com.gmartino.trees.extra.MAIL";
    public static final int TEXT_REQUEST = 1;

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

        boolean userExists = userViewModel.checkUserExists(mail, password);

        if (userExists) {
            Log.d(LOG_TAG, "User Logged In");
            User user = userViewModel.findUser(mail, password);

            //go to home

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
        startActivityForResult(intent, TEXT_REQUEST);

    }
}