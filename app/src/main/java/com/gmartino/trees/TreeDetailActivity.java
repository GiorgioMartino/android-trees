package com.gmartino.trees;

import static com.gmartino.trees.HomeActivity.EXTRA_TREE;
import static com.gmartino.trees.HomeActivity.EXTRA_USER;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gmartino.trees.dto.TreeDTO;
import com.gmartino.trees.dto.UserDTO;
import com.gmartino.trees.service.GardenViewModel;
import com.gmartino.trees.service.TreeFactory;
import com.gmartino.trees.service.UserFactory;

public class TreeDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = TreeDetailActivity.class.getSimpleName();

    private TextView nameTextView;
    private TextView scientificNameTextView;
    private TextView countryTextView;
    private TextView co2TextView;

    private UserDTO userDTO;
    private TreeDTO treeDTO;

    private GardenViewModel gardenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        gardenViewModel = new ViewModelProvider(this).get(GardenViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_detail);

        Intent intent = getIntent();
        treeDTO = intent.getParcelableExtra(EXTRA_TREE);
        userDTO = intent.getParcelableExtra(EXTRA_USER);

        nameTextView = findViewById(R.id.treeDetailNameText);
        scientificNameTextView = findViewById(R.id.treeDetailScientificNameText);
        countryTextView = findViewById(R.id.treeDetailCountryText);
        co2TextView = findViewById(R.id.treeDetailCO2Text);

        nameTextView.setText(treeDTO.getName());
        scientificNameTextView.setText(treeDTO.getScientificName());
        countryTextView.setText(treeDTO.getCountry());
        co2TextView.setText(treeDTO.getCo2());
    }

    public void callHome(View view) {
        Log.d(LOG_TAG, "called callHome");

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER, userDTO);
        startActivity(intent);
    }

    public void addToGarden(View view) {
        Log.d(LOG_TAG, "called addToGarden");

        gardenViewModel.addToGarden(UserFactory.userDTOToUser(userDTO),
                TreeFactory.treeDTOToTree(treeDTO));
    }
}