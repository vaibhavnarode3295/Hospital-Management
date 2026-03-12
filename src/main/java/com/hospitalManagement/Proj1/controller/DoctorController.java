package com.hospitalManagement.Proj1.controller;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.model.Prescription;
import com.hospitalManagement.Proj1.service.AppointmentService;
import com.hospitalManagement.Proj1.service.DoctorService;
import com.hospitalManagement.Proj1.service.PatientService;
import com.hospitalManagement.Proj1.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PrescriptionService prescriptionService;
    @GetMapping("/doctor")
    public String doctor(Model model, Principal principal)
    {
        Doctors doctors=doctorService.findRecord(principal.getName());
        Long patientsTretead=doctorService.countTotalPatientsTretead(doctors.getId());
        Long todaysPatients=doctorService.countTodaysPatients(doctors.getId());
        List<Appointment> appointmentList=doctorService.getAllSchedulePatients(doctors.getId());
        model.addAttribute("doctor",doctors);
        model.addAttribute("totalPatientsHandled",patientsTretead);
        model.addAttribute("todayPatients",todaysPatients);
        model.addAttribute("appointmentsToday",appointmentList);
        return "doctor-dashboard";
    }

    @GetMapping("/doctor/patients")
    public String getAllPatients(Model model, Principal principal)
    {
        Doctors doctors=doctorService.getDoctors(principal.getName());
        List<Appointment> appointmentList=doctorService.findAllPatients(doctors.getId());
        model.addAttribute("patients", appointmentList);
        return "doctor-patient-list";
    }

    @GetMapping("/doctor/appointments")
    public String getAllTodaysAppointment(Model model, Principal principal)
    {
        Doctors doctors=doctorService.findRecord(principal.getName());
        List<Appointment> appointmentList=doctorService.getAllSchedulePatients(doctors.getId());
        model.addAttribute("todaysAppointments",appointmentList);
        return "doctor-todays-appointment";
    }

    @GetMapping("/doctor/prescribe/{id}")
    public String prescribe(@PathVariable Long id,Model model)
    {
        Appointment appointment=appointmentService.findAppointment(id);
        model.addAttribute("appointment",appointment);
        Prescription prescription=new Prescription();
        model.addAttribute("prescription",prescription);
        return "prescribe";
    }

    @PostMapping("/doctor/savePrescription")
    public String savePrescription(@ModelAttribute Prescription prescription, Principal principal, @RequestParam Long patientId)
    {
        Doctors doctors=doctorService.findRecord(principal.getName());
        prescription.setDoctor(doctors);
        Patients patients=patientService.findPatientById(patientId);
        prescription.setPatient(patients);
        prescriptionService.savePrescription(prescription);
        return"prescription-success";
    }

    @GetMapping("/doctor/profile")
    public String profile(Model model, Principal principal)
    {
        Doctors doctors=doctorService.findRecord(principal.getName());
        model.addAttribute("doctor",doctors);
        return "doctor-status";
    }

    @PostMapping("/doctor/updateStatus")
    public String updateStatus(@ModelAttribute Doctors doctor)
    {
        doctorService.setDoctorStatus(doctor.getId(), doctor.getStatus());
        return "redirect:/doctor";
    }
}
