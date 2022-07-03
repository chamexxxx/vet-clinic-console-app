package com.chamexxxx.model;

import java.util.UUID;

public class Doctor {
    private final String id;
    private final String fullName;
    private final String specialization;

    public Doctor(String fullName, String specialization) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return String.format("\n\t ID: %s\n\t ФИО: %s\n\t Специализация: %s", id, fullName, specialization);
    }
}
