package com.gmartino.trees;

import static com.gmartino.trees.HomeActivity.EXTRA_TREE;
import static com.gmartino.trees.HomeActivity.EXTRA_USER;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmartino.trees.component.GardenTreeListAdapter;
import com.gmartino.trees.dto.UserDTO;
import com.gmartino.trees.entity.Garden;
import com.gmartino.trees.entity.UserTree;
import com.gmartino.trees.service.GardenViewModel;
import com.gmartino.trees.service.TreeFactory;
import com.gmartino.trees.service.TreeViewModel;
import com.gmartino.trees.service.UserFactory;
import com.gmartino.trees.service.UserTreeFactory;

import java.util.List;

public class GardenActivity extends AppCompatActivity {

    private static final String LOG_TAG = GardenActivity.class.getSimpleName();

    public static final String EXTRA_USER_TREE = "com.gmartino.trees.extra.USER_TREE";

    private GardenViewModel gardenViewModel;
    private TreeViewModel treeViewModel;

    private UserDTO userDTO;

    private GardenTreeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);

        Intent intent = getIntent();
        userDTO = intent.getParcelableExtra(EXTRA_USER);

        RecyclerView recyclerView = findViewById(R.id.homeTreesList);
        adapter = new GardenTreeListAdapter(new GardenTreeListAdapter.TreeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gardenViewModel = new ViewModelProvider(this).get(GardenViewModel.class);
        treeViewModel = new ViewModelProvider(this).get(TreeViewModel.class);

        adapter.setOnClickListener((position, tree) -> {
            Intent gardenDetailIntent = new Intent(this, GardenDetailActivity.class);
            gardenDetailIntent.putExtra(EXTRA_TREE,
                    TreeFactory.treeToTreeDTO(treeViewModel.findByName(tree.getTreeName())));
            gardenDetailIntent.putExtra(EXTRA_USER_TREE, UserTreeFactory.userTreeToDTO(tree));
            gardenDetailIntent.putExtra(EXTRA_USER, userDTO);
            startActivity(gardenDetailIntent);
        });
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onResume");

        super.onResume();
        List<UserTree> userTrees =
                gardenViewModel.getGardenByUser(UserFactory.userDTOToUser(userDTO));

        adapter.submitList(userTrees);

    }

    public void callHome(View view) {
        Log.d(LOG_TAG, "called callHome");

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USER, userDTO);
        startActivity(intent);
    }
}