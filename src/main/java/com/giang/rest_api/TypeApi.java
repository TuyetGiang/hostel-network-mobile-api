package com.giang.rest_api;

import com.giang.service.dto.TypeDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("/types")
public interface TypeApi {
    @ApiOperation(tags = "BENEFIT", value = "Get all type", response = TypeDTO.class)
    @GetMapping("")
    ResponseEntity<List<TypeDTO>> getAll();

    @ApiOperation(tags = "BENEFIT", value = "Get a benefit", response = TypeDTO.class)
    @GetMapping("/{id}")
    ResponseEntity<TypeDTO> getBenefit(@PathVariable("id") Integer id);
}
