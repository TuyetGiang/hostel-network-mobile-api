package com.giang.rest_api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping("/rooms")
public interface RoomApi {

//    @ApiOperation(tags = {"ROOM"}, response = ApartmentDTO.class, value = "Get all apartment")
//    @GetMapping("")
//    ResponseEntity<List<ApartmentDTO>> getAllApartment();
//
//    @ApiOperation(tags = {"APARTMENT"}, response = ApartmentDTO.class, value = "Add a benefit into apartment")
//    @PostMapping("/{id}/benefits")
//    ResponseEntity<ApartmentDTO> addBenefitToApartment(@PathVariable("id") Integer id,
//                                                       @RequestParam Integer benefitId);
}
