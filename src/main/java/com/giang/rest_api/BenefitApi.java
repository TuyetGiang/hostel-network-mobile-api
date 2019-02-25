package com.giang.rest_api;

import com.giang.service.dto.BenefitDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/benefits")
public interface BenefitApi {

    @ApiOperation(tags = "BENEFIT", value = "Get all benefit", response = BenefitDTO.class)
    @GetMapping("")
    ResponseEntity<List<BenefitDTO>> getAll();

    @ApiOperation(tags = "BENEFIT", value = "Get a benefit", response = BenefitDTO.class)
    @GetMapping("/{id}")
    ResponseEntity<BenefitDTO> getBenefit(@PathVariable("id") Integer id);


}
