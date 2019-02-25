package com.giang.rest_api.impl;

import com.giang.rest_api.WishListApi;
import com.giang.service.dto.WishListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListController implements WishListApi {
    @Override
    public ResponseEntity<WishListDTO> getWishListOfUser(Integer id) {
        return ResponseEntity.ok(new WishListDTO());
    }

    @Override
    public ResponseEntity<WishListDTO> createNewWishList(WishListDTO newWishList) {
        return ResponseEntity.ok(new WishListDTO());
    }

    @Override
    public ResponseEntity<Boolean> deleteAWishList(Integer id) {
        return ResponseEntity.ok(true);
    }
}
