package com.gmartino.trees.service;

import com.gmartino.trees.dto.UserTreeDTO;
import com.gmartino.trees.entity.UserTree;

public class UserTreeFactory {

    public static UserTreeDTO userTreeToDTO(UserTree userTree) {
        return new UserTreeDTO(userTree.getId(), userTree.getUserEmail(), userTree.getTreeName(),
                userTree.getAddedDate(),
                userTree.getNickname());
    }

    public static UserTree dtoToUserTree(UserTreeDTO dto) {
        return new UserTree(dto.getId(), dto.getUserEmail(), dto.getTreeName(), dto.getAddedDate(),
                dto.getNickname());
    }
}
