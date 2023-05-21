package com.gmartino.trees.service;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gmartino.trees.dao.TreeDatabase;
import com.gmartino.trees.entity.Tree;
import com.gmartino.trees.repository.TreeRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TreeViewModel extends AndroidViewModel {

    private static final String LOG_TAG = TreeViewModel.class.getSimpleName();

    private TreeRepository treeRepository;

    private LiveData<List<Tree>> trees;

    public TreeViewModel(@NonNull Application application) {
        super(application);
        treeRepository = new TreeRepository(application);
        trees = treeRepository.getLiveTrees();
    }

    public LiveData<List<Tree>> getTrees() {
        return trees;
    }

    public List<Tree> getAll() {
        return treeRepository.getAll();
    }

    public Tree findByName(String name) {
        return treeRepository.findByName(name);
    }

    public void initTrees() {
        treeRepository.insert(new Tree("Mango", "Mangifera indica", "Ghana", "-700kg"));
        treeRepository.insert(new Tree("Avocado", "Persea americana", "Colombia", "-500kg"));
        treeRepository.insert(new Tree("Cacao", "Theobroma cacao", "Cameroon", "-55kg"));
    }

}
