package com.giang.rest_api;

import com.giang.service.dto.PostDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/posts")
public interface PostApi {

    @ApiOperation(tags = "POST", value = "Get all post", response = PostDTO.class)
    @GetMapping("")
    ResponseEntity<List<PostDTO>> getAll();

    @ApiOperation(tags = "POST", value = "Get a post", response = PostDTO.class)
    @GetMapping("/{id}")
    ResponseEntity<PostDTO> getPost(@PathVariable("id") Integer id);

    @ApiOperation(tags = {"POST"}, value = "Create new a post", response = PostDTO.class)
    @PostMapping("")
    ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO newPost);

    @ApiOperation(tags = {"POST"}, value = "Modify  a post", response = PostDTO.class)
    @PutMapping("")
    ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO dto);


    @ApiOperation(tags = "POST", value = "Filter post by  benefit, type and location", response = PostDTO.class)
    @GetMapping("/conditions")
    ResponseEntity<List<PostDTO>> filterPost(@RequestParam(value = "benefits", required = false) List<Integer> benefitIds,
                                             @RequestParam(value = "type", required = false) Integer typeId,
                                             @RequestParam(value = "location", required = false) String location);


}
