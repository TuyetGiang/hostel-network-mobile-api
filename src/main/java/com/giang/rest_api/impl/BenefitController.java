package com.giang.rest_api.impl;

import com.giang.rest_api.BenefitApi;
import com.giang.service.dto.BenefitDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BenefitController implements BenefitApi {

    @Override
    public ResponseEntity<List<BenefitDTO>> getAll() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<BenefitDTO> getBenefit(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new BenefitDTO());
    }
}
