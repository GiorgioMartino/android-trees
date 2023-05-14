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
}
