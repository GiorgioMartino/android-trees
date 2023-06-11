package com.gmartino.trees;

import static com.gmartino.trees.GardenActivity.EXTRA_USER_TREE;
import static com.gmartino.trees.HomeActivity.EXTRA_TREE;
import static com.gmartino.trees.HomeActivity.EXTRA_USER;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gmartino.trees.dto.TreeDTO;
import com.gmartino.trees.dto.UserDTO;
import com.gmartino.trees.dto.UserTreeDTO;
import com.gmartino.trees.entity.UserTree;
import com.gmartino.trees.service.GardenViewModel;
import com.gmartino.trees.service.UserTreeFactory;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;

public class GardenDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = GardenDetailActivity.class.getSimpleName();

    private static final String NICKNAME_TEXT_STATE = "nicknameTextState";

    private TextView nameTextView;
    private TextView scientificNameTextView;
    private TextView countryTextView;
    private TextView co2TextView;
    private TextView dateAddedTextView;
    private EditText nicknameEditText;

    private UserDTO userDTO;
    private TreeDTO treeDTO;
    private UserTreeDTO userTreeDTO;

    private GardenViewModel gardenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        gardenViewModel = new ViewModelProvider(this).get(GardenViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_detail);

        Intent intent = getIntent();
        treeDTO = intent.getParcelableExtra(EXTRA_TREE);
        userDTO = intent.getParcelableExtra(EXTRA_USER);
        userTreeDTO = intent.getParcelableExtra(EXTRA_USER_TREE);

        nameTextView = findViewById(R.id.treeDetailNameText);
        scientificNameTextView = findViewById(R.id.treeDetailScientificNameText);
        countryTextView = findViewById(R.id.treeDetailCountryText);
        co2TextView = findViewById(R.id.treeDetailCO2Text);
        dateAddedTextView = findViewById(R.id.treeDetailAddedDateText);
        nicknameEditText = findViewById(R.id.treeDetailNicknameText);

        if (savedInstanceState != null)
            nicknameEditText.setText(savedInstanceState.getString(NICKNAME_TEXT_STATE));

        String formattedDate = DateFormat.getDateTimeInstance().format(userTreeDTO.getAddedDate());
        nameTextView.setText(treeDTO.getName());
        scientificNameTextView.setText(treeDTO.getScientificName());
        countryTextView.setText(treeDTO.getCountry());
        co2TextView.setText(treeDTO.getCo2());
        dateAddedTextView.setText(formattedDate);
        nicknameEditText.setText(userTreeDTO.getNickname());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NICKNAME_TEXT_STATE, nicknameEditText.getText().toString());
    }

    public void callHome(View view) {
        Log.d(LOG_TAG, "called callHome");

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER, userDTO);
        startActivity(intent);
    }

    public void saveNickname(View view) {
        Log.d(LOG_TAG, "SAVE Button clicked!");

        String nickname = nicknameEditText.getText().toString();

        UserTree userTree = UserTreeFactory.dtoToUserTree(userTreeDTO);
        userTree.setNickname(nickname);

        gardenViewModel.update(userTree);
        nicknameEditText.setText(nickname);

        Snackbar.make(findViewById(R.id.gardenDetailCoordinatorLayout),
                R.string.garden_detail_snackbar,
                Snackbar.LENGTH_SHORT).show();
    }
}