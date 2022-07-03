package com.chamexxxx.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Appointment {
    public enum Status {
        NEW("Новый"),
        PROCESS("В процессе"),
        CANCELLED("Отменен"),
        AWAITING("Ожидает оплаты"),
        COMPLETED("Завершен");

        public final String message;

        Status(String message) {
            this.message = message;
        }
    }

    private final String id;
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDateTime date;

    private Status status;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime date) {
        this.id = UUID.randomUUID().toString();
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.status = Status.NEW;
    }

    public String getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
            "\n\tID: %s \n\tДата и время записи: %s \n\tСтатус: %s \n\tДоктор: %s \n\tПациент: %s\n",
            id, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), status, doctor, patient
        );
    }
}
