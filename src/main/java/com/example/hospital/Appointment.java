package com.example.hospital;

public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private String appointmentDate;

    public Appointment(int id, int patientId, int doctorId, String appointmentDate) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}