package com.gmartino.trees.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.gmartino.trees.dao.TreeDao;
import com.gmartino.trees.dao.TreeDatabase;
import com.gmartino.trees.entity.Tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TreeRepository {

    private static final String LOG_TAG = TreeRepository.class.getSimpleName();

    private TreeDao treeDao;
    private LiveData<List<Tree>> trees;

    public TreeRepository(Application application) {
        TreeDatabase db = TreeDatabase.getDatabase(application);
        treeDao = db.treeDao();
        trees = treeDao.getTrees();
    }

    public LiveData<List<Tree>> getLiveTrees() {
        return trees;
    }

    public List<Tree> getAll() {
        try {
            return TreeDatabase.databaseWriteExecutor.submit(() -> treeDao.getAll()).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d(LOG_TAG, "No Trees found");
            return Collections.emptyList();
        }
    }

    public void insert(Tree tree) {
        TreeDatabase.databaseWriteExecutor.execute(() -> treeDao.insert(tree));
    }
}
