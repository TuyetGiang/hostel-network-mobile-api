package com.giang.rest_api;

import com.giang.service.dto.AppointmentDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("appointments")
public interface AppointmentApi {
    @ApiOperation(tags = {"APPOINTMENT"}, response = AppointmentDTO.class, value = "Get all appointments")
    @GetMapping("")
    ResponseEntity<List<AppointmentDTO>> getAllAppointment();

    @ApiOperation(tags = {"APPOINTMENT"}, response = AppointmentDTO.class, value = "Get an appointments")
    @GetMapping("/{id}")
    ResponseEntity<AppointmentDTO> getAnAppointment(@PathVariable("id")Integer id);

    @ApiOperation(tags = {"APPOINTMENT"}, response = AppointmentDTO.class, value = "Get all appointments of an user")
    @GetMapping("/{user-id}")
    ResponseEntity<List<AppointmentDTO>> getAllAppointmentByUser(@PathVariable("user-id") Integer userId);

    @ApiOperation(tags = {"APPOINTMENT"}, response = AppointmentDTO.class, value = "Create new appointment")
    @PostMapping("/")
    ResponseEntity<AppointmentDTO> createNewAppointment(@RequestBody AppointmentDTO newAppointment);

    @ApiOperation(tags = {"APPOINTMENT"}, response = AppointmentDTO.class, value = "Update status an appointments(waiting/accepted/canceled)")
    @PutMapping("/{id}")
    ResponseEntity<AppointmentDTO> updateStatusAppointment(@PathVariable("id")Integer id,
                                                           @RequestParam Integer status);

    @ApiOperation(tags = {"APPOINTMENT"}, response = Boolean.class, value = "Delete an appointments")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteAnAppointment(@PathVariable("id")Integer id);
}
