package com.chamexxxx.repository;

import com.chamexxxx.model.Doctor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DoctorRepository {
    private static final Set<Doctor> DOCTORS = new HashSet<>();

    private static final DoctorRepository instance = new DoctorRepository();

    public static DoctorRepository getInstance() {
        return instance;
    }

    public Set<Doctor> findAll() {
        return DOCTORS;
    }

    public Optional<Doctor> findById(String id) {
        return DOCTORS.stream().filter(doctor -> doctor.getId().equals(id)).findFirst();
    }

    public void save(Doctor doctor) {
        DOCTORS.add(doctor);
    }
}
