package com.giang.rest_api.impl;

import com.giang.rest_api.AppointmentApi;
import com.giang.service.dto.AppointmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppointmentController implements AppointmentApi {
    @Override
    public ResponseEntity<List<AppointmentDTO>> getAllAppointment() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<AppointmentDTO> getAnAppointment(Integer id) {
        return ResponseEntity.ok(new AppointmentDTO());
    }

    @Override
    public ResponseEntity<List<AppointmentDTO>> getAllAppointmentByUser(Integer userId) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<AppointmentDTO> createNewAppointment(AppointmentDTO newAppointment) {
        return ResponseEntity.ok(new AppointmentDTO());
    }

    @Override
    public ResponseEntity<AppointmentDTO> updateStatusAppointment(Integer id, Integer status) {
        return ResponseEntity.ok(new AppointmentDTO());
    }

    @Override
    public ResponseEntity<Boolean> deleteAnAppointment(Integer id) {
        return ResponseEntity.ok(true);
    }
}
