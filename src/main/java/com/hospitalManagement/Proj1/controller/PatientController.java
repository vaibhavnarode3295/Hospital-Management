package com.hospitalManagement.Proj1.controller;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.model.Prescription;
import com.hospitalManagement.Proj1.service.AppointmentService;
import com.hospitalManagement.Proj1.service.PatientService;
import com.hospitalManagement.Proj1.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PrescriptionService prescriptionService;
    @GetMapping("/patient")
    public String dashboard(Model model, Principal principal)
    {
        Patients patient = patientService.findPatinet(principal.getName());
        List<Appointment> appointment=appointmentService.findAppointmentForPatient(patient.getId());
        model.addAttribute("patient",patient);
        model.addAttribute("appointments",appointment);
        return "patient-dashboard";
    }
    @GetMapping("/patient/book-appointment")
    public String bookAppointment(Model model)
    {
        model.addAttribute("appointment", new Appointment());
        return "bookAppointment";
    }
    @PostMapping("/patient/appointment")
    public String appointment(@ModelAttribute Appointment appointment, Principal principal)
    {
        Patients patient = patientService.findPatinet(principal.getName());
        if(appointmentService.bookAppointment(appointment,patient))
            return "appointment-success";
        else {
            return "appointment-fail";
        }
    }
    @GetMapping("/patient/doctors")
    public String viewDoctors(Model model)
    {
        List<Doctors> doctorsList = patientService.findAllDoctors();
        model.addAttribute("doctors",doctorsList);
        return "patient-doctors";
    }
    @GetMapping("/patient-doctor/profile/{id}")
    public String viewDoctorProfile(@PathVariable("id") Long id, Model model) {
       Doctors doctors= patientService.findDoctorById(id);
       model.addAttribute("doctor",doctors);
       return "patient-doctorProfile";
    }

    @GetMapping("/user/prescriptions")
    public String seePrescription(Model model, Principal principal)
    {
        Patients patients= patientService.findPatinet(principal.getName());
        List<Prescription> list = prescriptionService.getList(patients.getId());
        model.addAttribute("prescriptions",list);
        return "prescription";
    }

    @GetMapping("/patient/today's-appointment")
    public String pendingAppointment(Model model, Principal principal){
        List<Appointment> list=patientService.todayAppointment(principal.getName());
        model.addAttribute("list",list);
        return "today's-Appointment";
    }
}
