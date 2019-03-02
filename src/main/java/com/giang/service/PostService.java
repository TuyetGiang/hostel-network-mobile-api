package com.giang.service;

import com.giang.service.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();

    PostDTO getPost(Integer id);

    PostDTO createNewPost(PostDTO newPost);

    PostDTO updatePost(PostDTO dto);

    List<PostDTO> fillterPost(List<Integer> benefitIds, Integer typeId, String location, Double minPrice, Double maxPrice);

    PostDTO pushPost(Integer id);

    PostDTO repostPost(Integer id, Integer numberDay, Boolean isPush);

    Boolean deletePost(Integer id);
}
