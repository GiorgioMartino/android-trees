package com.gmartino.trees.service;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gmartino.trees.entity.Tree;
import com.gmartino.trees.entity.User;
import com.gmartino.trees.entity.UserTree;
import com.gmartino.trees.repository.GardenRepository;

import java.util.Date;
import java.util.List;

public class GardenViewModel extends AndroidViewModel {

    private static final String LOG_TAG = GardenViewModel.class.getSimpleName();

    private final GardenRepository gardenRepository;

    public GardenViewModel(@NonNull Application application) {
        super(application);
        gardenRepository = new GardenRepository(application);
    }

    public UserTree addToGarden(User user, Tree tree) {
        Log.d(LOG_TAG, String.format("Adding %s to %s Garden", tree.getName(), user.getEmail()));

        UserTree userTree = new UserTree(user.getEmail(), tree.getName(), new Date(),
                tree.getScientificName());
        gardenRepository.insert(userTree);
        return userTree;
    }

    public List<UserTree> getGardenByUser(User user) {
        Log.d(LOG_TAG, String.format("Getting Garden for User %s", user.getEmail()));
        return gardenRepository.getUserTrees(user);
    }

    public UserTree update(UserTree userTree) {
        Log.d(LOG_TAG, String.format("Updating %s to %s Garden", userTree.getTreeName(), userTree.getUserEmail()));
        gardenRepository.update(userTree);
        return userTree;
    }
}
