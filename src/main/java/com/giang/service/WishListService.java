package com.giang.service;

import com.giang.service.dto.WishListDTO;

import java.util.List;

public interface WishListService {
    List<Integer> getAllPostIdInWishList(Integer userId);

    WishListDTO savePostToWishList(Integer userId, Integer postId);

    Boolean deletePostOutOfWishList(Integer id);
}
