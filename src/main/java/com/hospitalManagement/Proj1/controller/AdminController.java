package com.hospitalManagement.Proj1.controller;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String dashboard(Model model)
    {
        model.addAttribute("adminName","Admin");
        model.addAttribute("doctorCount",adminService.countTotalDoctors());
        model.addAttribute("patientCount",adminService.countTotalPatients());
        model.addAttribute("appointmentsToday",10);
        model.addAttribute("revenue",1000000);
        return "admin-dashboard";
    }

    @GetMapping("/admin/doctors")
    public String addDoctors(Model model)
    {
        model.addAttribute("doctor",new Doctors());
        return "manageDoctors";
    }
    @PostMapping("/registerDoctor")
    public String saveDoctor(@ModelAttribute Doctors doctors)
    {
        adminService.saveDoctor(doctors);
        return "doctor-success";
    }

    @GetMapping("/admin/patients")
    public String addPatents(Model model)
    {
        model.addAttribute("patient",new Patients());
        return "managePatients";
    }
    @PostMapping("/registerPatient")
    public String savePatients(@ModelAttribute Patients patients)
    {
        return "patient-success";
    }

    @GetMapping("/admin/seePatients")
    public String seeAllPatients(Model model)
    {
        model.addAttribute("patients", adminService.findAllPatients());
        return "list-patients";
    }

    @GetMapping("/admin/seeDoctors")
    public String sellAllDoctors(Model model)
    {
        model.addAttribute("doctors", adminService.findAllDoctors());
        return "list-doctors";
    }

    @GetMapping("/admin/appointments")
    public String seeAllAppointments(Model model)
    {
        List<Appointment> appointmentList= adminService.getAllAppointments();
        model.addAttribute("appointments",appointmentList);
        return "admin-all-appointments";
    }

    @GetMapping("/admin/doctor/delete/{id}")
    public String deleteDoctors(Model model, @PathVariable Long id)
    {
        adminService.deleteDoctorById(id);
        return "redirect:/admin/seeDoctors";
    }

    @GetMapping("/admin/doctor/edit/{id}")
    public String editDoctors(@PathVariable Long id, Model model)
    {
        Doctors doctors=adminService.getDoctors(id);
        model.addAttribute("doctor",doctors);
        return "doctors-edit";
    }

    @PostMapping("/admin/doctor/update")
    public String update(@ModelAttribute Doctors doctor)
    {
        adminService.editDoctors(doctor);
        return "redirect:/admin/seeDoctors";
    }


}
