package com.gmartino.trees;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmartino.trees.component.TreeListAdapter;
import com.gmartino.trees.dto.UserDTO;
import com.gmartino.trees.service.TreeFactory;
import com.gmartino.trees.service.TreeViewModel;

public class HomeActivity extends AppCompatActivity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();

    public static final String EXTRA_TREE = "com.gmartino.trees.extra.TREE";
    public static final String EXTRA_USER = "com.gmartino.trees.extra.USER";

    private TreeViewModel treeViewModel;

    private UserDTO userDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        userDTO = intent.getParcelableExtra(EXTRA_USER);

        RecyclerView recyclerView = findViewById(R.id.homeTreesList);
        final TreeListAdapter adapter = new TreeListAdapter(new TreeListAdapter.TreeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        treeViewModel = new ViewModelProvider(this).get(TreeViewModel.class);
        treeViewModel.getTrees().observe(this, adapter::submitList);

        adapter.setOnClickListener((position, tree) -> {
            Intent treeDetailIntent = new Intent(this, TreeDetailActivity.class);
            treeDetailIntent.putExtra(EXTRA_TREE, TreeFactory.treeToTreeDTO(tree));
            treeDetailIntent.putExtra(EXTRA_USER, userDTO);
            startActivity(treeDetailIntent);
        });

        if (treeViewModel.getAll().isEmpty()) {
            treeViewModel.initTrees();
        }
    }

    public void openMyGarden(View view) {
        Log.d(LOG_TAG, "called openMyGarden");

        Intent gardenIntent = new Intent(this, GardenActivity.class);
        gardenIntent.putExtra(EXTRA_USER, userDTO);
        startActivity(gardenIntent);
    }
}