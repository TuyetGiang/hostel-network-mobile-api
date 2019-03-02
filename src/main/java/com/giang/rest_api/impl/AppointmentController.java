package com.giang.rest_api.impl;

import com.giang.rest_api.AppointmentApi;
import com.giang.service.AppointmentService;
import com.giang.service.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController implements AppointmentApi {

    @Autowired
    AppointmentService appointmentService;

    @Override
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByRenter(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentByRenter(userId));
    }

    @Override
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByHost(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentByHost(userId));
    }

    @Override
    public ResponseEntity<AppointmentDTO> getAppointmentDetail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(appointmentService.getAppointmentDetail(id));
    }

    @Override
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO newAppointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(newAppointment));
    }

    @Override
    public ResponseEntity<AppointmentDTO> updateStatus(@PathVariable("id") Integer id,
                                                       @RequestParam Integer status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    @Override
    public ResponseEntity<Boolean> deleteAppointment(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
