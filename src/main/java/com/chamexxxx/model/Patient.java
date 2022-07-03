package com.chamexxxx.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Patient {
    private final String id;
    private String name;
    private final LocalDate registrationDate;

    public Patient(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.registrationDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var patient = (Patient) obj;

        return Objects.equals(id, patient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("\n\t ID: %s\n\t Имя: %s\n\t Дата регистрации: %s\n", id, name, registrationDate);
    }
}
