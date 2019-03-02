package com.giang.service.impl;

import com.giang.repository.AppointmentRepository;
import com.giang.repository.entity.Appointment;
import com.giang.service.AppointmentService;
import com.giang.service.dto.AppointmentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceimpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceimpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<AppointmentDTO> getAppointmentByRenter(Integer userId) {
        return appointmentRepository.findAllByRenterId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentByHost(Integer userId) {
        return appointmentRepository.findAllByHostId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentDetail(Integer id) {
        return mapToDto(appointmentRepository.findById(id));
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO newAppointment) {
        Appointment appointment = mapToEntity(newAppointment);
        appointment = appointmentRepository.saveAndFlush(appointment);

        return mapToDto(appointment);
    }

    @Override
    public AppointmentDTO updateStatus(Integer id, Integer status) {
        Appointment appointment = appointmentRepository.findById(id);
        Optional.ofNullable(appointment).orElseThrow(EntityNotFoundException::new);

        appointment.setStatus(status);
        appointment = appointmentRepository.saveAndFlush(appointment);
        return mapToDto(appointment);
    }

    @Override
    public Boolean deleteAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findById(id);
        Optional.ofNullable(appointment).orElseThrow(EntityNotFoundException::new);

        appointmentRepository.delete(appointment);
        return true;
    }

    private AppointmentDTO mapToDto(Appointment entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, AppointmentDTO.class);
    }

    private Appointment mapToEntity(AppointmentDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Appointment.class);
    }
}
