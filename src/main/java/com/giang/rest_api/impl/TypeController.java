package com.giang.rest_api.impl;

import com.giang.rest_api.TypeApi;
import com.giang.service.dto.TypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TypeController implements TypeApi {
    @Override
    public ResponseEntity<List<TypeDTO>> getAll() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<TypeDTO> getBenefit(Integer id) {
        return ResponseEntity.ok(new TypeDTO());
    }
}
