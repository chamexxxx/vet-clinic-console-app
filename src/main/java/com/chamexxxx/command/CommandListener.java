package com.chamexxxx.command;

import com.chamexxxx.Message;
import com.chamexxxx.annotation.Command;
import com.chamexxxx.annotation.Option;
import com.chamexxxx.model.Appointment;
import com.chamexxxx.model.Doctor;
import com.chamexxxx.model.Patient;
import com.chamexxxx.repository.AppointmentRepository;
import com.chamexxxx.repository.DoctorRepository;
import com.chamexxxx.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@com.chamexxxx.annotation.CommandContainer(
    prompt = "Выберите действие",
    exitPrompt = "Выйти"
)
public class CommandListener {
    @Command(name = "Создание доктора", order = 1)
    public String createDoctor(
        @Option(prompt = "ФИО") String fullName,
        @Option(prompt = "Специализация") String specialization
    ) {
        var doctor = new Doctor(fullName, specialization);

        DoctorRepository.getInstance().save(doctor);

        return Message.success("Доктор создан с id: " + doctor.getId());
    }

    @Command(name = "Создание пациента", order = 2)
    public String createPatient(@Option(prompt = "Имя") String name) {
        var patient = new Patient(name);

        PatientRepository.getInstance().save(patient);

        return Message.success("Пациент создан с id: " + patient.getId());
    }

    @Command(name = "Создание приема пациента к доктору", order = 3)
    public String createAppointment(
        @Option(prompt = "ID доктора") String doctorId,
        @Option(prompt = "ID пациента") String patientId,
        @Option(
            prompt = "Дата",
            pattern = "dd.MM.yyyy HH:mm"
        ) LocalDateTime dateTime
    ) {
        var doctor = DoctorRepository.getInstance().findById(doctorId);

        if (doctor.isEmpty()) {
            return Message.error("Доктор с таким id не существует");
        }

        var patient = PatientRepository.getInstance().findById(patientId);

        if (patient.isEmpty()) {
            return Message.error("Пациент с таким id не существует");
        }

        var appointment = new Appointment(doctor.get(), patient.get(), dateTime);

        AppointmentRepository.getInstance().save(appointment);

        return Message.success("Приём создан с id: " + appointment.getId());
    }

    @Command(name = "Изменение статуса приема", order = 4)
    public String updateAppointment(
        @Option(prompt = "ID приема") String id,
        @Option(
            prompt = "Новый статус",
            choices = {"NEW", "PROCESS", "CANCELLED", "AWAITING", "COMPLETED"}
        ) String status
    ) {
        var appointment = AppointmentRepository.getInstance().findById(id);

        if (appointment.isEmpty()) {
            return Message.error("Приема с таким id не существует");
        }

        appointment.get().setStatus(Appointment.Status.valueOf(status));

        return Message.success("Статус приема был обновлен");
    }

    @Command(name = "Редактирование пациента", order = 5)
    public String updatePatient(
        @Option(prompt = "ID пациента") String id,
        @Option(prompt = "Новое имя пациента") String name
    ) {
        var patient = PatientRepository.getInstance().findById(id);

        if (patient.isEmpty()) {
            return Message.error("Пациент с таким id не найден");
        }

        patient.get().setName(name);

        return Message.success("Данные пациента обновлены");
    }

    @Command(name = "Вывод всех приемов определенного пациента", order = 6)
    public String getAppointments(@Option(prompt = "ID пациента") String patientId) {
        var appointments = AppointmentRepository.getInstance()
                .findAll()
                .stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .toArray();

        if (appointments.length == 0) {
            return Message.info("Приемов не найдено");
        }

        return Message.success(Arrays.toString(appointments));
    }

    @Command(name = "Вывод всех пациентов", order = 7)
    public String getPatients() {
        var patients = PatientRepository.getInstance().findAll();

        if (patients.size() == 0) {
            return Message.success("Нет ни одного пациента");
        }

        return Message.success(patients.toString());
    }

    @Command(name = "Удаление пациента", order = 8)
    public String deletePatient(@Option(prompt = "ID пациента") String id) {
        var deleted = PatientRepository.getInstance()
                .findAll()
                .removeIf(patient -> patient.getId().equals(id));

        if (!deleted) {
            return Message.error("Пациент с таким id не существует");
        }

        return Message.success("Данные пациента были удалены");
    }
}
