package com.giang.rest_api.impl;

import com.giang.rest_api.PostApi;
import com.giang.service.PostService;
import com.giang.service.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class PostController implements PostApi {

    @Autowired
    PostService postService;

//    @Override
//    public ResponseEntity<List<PostDTO>> getAll() {
//        return ResponseEntity.ok(postService.getAll());
//    }

    @Override
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @Override
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO newPost) {
        return ResponseEntity.ok(postService.createNewPost(newPost));
    }

    @Override
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id")Integer id,
                                              @RequestParam(value = "pushed", required = false)Boolean pushed,
                                              @RequestParam(value = "reposted", required = false)Boolean reposted,
                                              @RequestBody(required = false) PostDTO dto) {
        if (Objects.nonNull(pushed) && pushed){
            return ResponseEntity.ok(postService.pushPost(id));
        }
        if (Objects.nonNull(reposted) && reposted){
            return ResponseEntity.ok(postService.repostPost(id, dto.getDueDate(), dto.getPush()));

        }
        return ResponseEntity.ok(postService.updatePost(dto));
    }

    @Override
    public ResponseEntity<List<PostDTO>> filterPost(@RequestParam(value = "benefits", required = false) List<Integer> benefitIds,
                                                    @RequestParam(value = "type", required = false) Integer typeId,
                                                    @RequestParam(value = "location", required = false) String location,
                                                    @RequestParam(value = "minPrice", required = false) Double minPrice,
                                                    @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        if (Objects.isNull(benefitIds) && Objects.isNull(typeId) && Objects.isNull(location) && Objects.isNull(minPrice) && Objects.isNull(maxPrice)) {
            return ResponseEntity.ok(postService.getAll());
        }
        return ResponseEntity.ok(postService.fillterPost(benefitIds, typeId, location, minPrice, maxPrice));
    }

//    @Override
//    public ResponseEntity<PostDTO> pushPost(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(postService.pushPost(id));
//    }
//
//    @Override
//    public ResponseEntity<PostDTO> repostPost(@PathVariable("id") Integer id,
//                                              @RequestParam Integer numberDay,
//                                              @RequestParam Boolean isPush) {
//        return ResponseEntity.ok(postService.repostPost(id, numberDay, isPush));
//    }

    @Override
    public ResponseEntity<Boolean> deletePost(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
