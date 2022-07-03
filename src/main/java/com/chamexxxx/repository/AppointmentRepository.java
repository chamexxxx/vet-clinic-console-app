package com.chamexxxx.repository;

import com.chamexxxx.model.Appointment;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AppointmentRepository {
    private static final Set<Appointment> APPOINTMENTS = new HashSet<>();

    private static final AppointmentRepository instance = new AppointmentRepository();

    public static AppointmentRepository getInstance() {
        return instance;
    }

    public Set<Appointment> findAll() {
        return APPOINTMENTS;
    }

    public Optional<Appointment> findById(String id) {
        return APPOINTMENTS.stream().filter(patient -> patient.getId().equals(id)).findFirst();
    }

    public void save(Appointment patient) {
        APPOINTMENTS.add(patient);
    }
}
