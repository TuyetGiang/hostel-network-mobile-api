package com.giang.rest_api;

import com.giang.service.dto.WishListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping("wishLists")
public interface WishListApi {

    @ApiOperation(tags = "WISHLIST", value = "Get all post in wish list of an user", response = WishListDTO.class)
    @GetMapping("/{id}")
    ResponseEntity<WishListDTO> getWishListOfUser(@PathVariable("id") Integer id);

    @ApiOperation(tags = "WISHLIST", value = "Add a post to wish list", response = WishListDTO.class)
    @PostMapping("")
    ResponseEntity<WishListDTO> createNewWishList(@RequestBody WishListDTO newWishList);

    @ApiOperation(tags = "WISHLIST", value = "Delete a post out off Wish list", response = Boolean.class)
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteAWishList(@PathVariable("id") Integer id);

}
