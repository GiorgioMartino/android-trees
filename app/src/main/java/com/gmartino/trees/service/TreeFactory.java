package com.gmartino.trees.service;

import com.gmartino.trees.dto.TreeDTO;
import com.gmartino.trees.entity.Tree;

public class TreeFactory {

    public static TreeDTO treeToTreeDTO(Tree tree) {
        return new TreeDTO(tree.getName(), tree.getScientificName(), tree.getCountry(),
                tree.getCo2());
    }

    public static Tree treeDTOToTree(TreeDTO treeDTO) {
        return new Tree(treeDTO.getName(), treeDTO.getScientificName(), treeDTO.getCountry(),
                treeDTO.getCo2());
    }

}
