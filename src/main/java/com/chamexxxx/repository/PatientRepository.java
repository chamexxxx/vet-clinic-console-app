package com.chamexxxx.repository;

import com.chamexxxx.model.Patient;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PatientRepository {
    private static final Set<Patient> PATIENTS = new HashSet<>();

    private static final PatientRepository instance = new PatientRepository();

    public static PatientRepository getInstance() {
        return instance;
    }

    public Set<Patient> findAll() {
        return PATIENTS;
    }

    public Optional<Patient> findById(String id) {
        return PATIENTS.stream().filter(patient -> patient.getId().equals(id)).findFirst();
    }

    public void save(Patient patient) {
        PATIENTS.add(patient);
    }
}
