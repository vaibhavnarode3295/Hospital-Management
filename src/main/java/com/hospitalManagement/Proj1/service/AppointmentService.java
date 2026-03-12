package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Appointment;
import com.hospitalManagement.Proj1.model.Doctors;
import com.hospitalManagement.Proj1.model.Patients;
import com.hospitalManagement.Proj1.repository.AppointmentRepo;
import com.hospitalManagement.Proj1.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    public boolean bookAppointment(Appointment appointment, Patients patients)
    {
        if(appointment != null)
        {
            String specification = appointment.getSpecification();
            System.out.println("You enter in appointment object");
            List<Doctors> doctorslist = doctorRepo.findBySpecializationAndStatus(specification,"Available");
            if(doctorslist == null || doctorslist.isEmpty()) {
//                throw new IllegalArgumentException("No available doctor found for this specialization");
                return false;
            }

            Integer minValue;
            Doctors selectedDoctor = null;
            Long minCount = Long.MAX_VALUE;

            for (Doctors doc : doctorslist) {
                Long count = appointmentRepo.countPatientsByDoctorId(doc.getId());

                if (count < minCount) {
                    minCount = count;
                    selectedDoctor = doc;
                }
            }

            if (selectedDoctor == null) {
                throw new IllegalArgumentException("Cannot assign doctor");
            }

            Appointment app = new Appointment();
            app.setFullName(appointment.getFullName());
            app.setAge(appointment.getAge());
            app.setGender(appointment.getGender());
            app.setReason(appointment.getReason());
            app.setDoctor(selectedDoctor);
            app.setSpecification(appointment.getSpecification());
            app.setAppointmentTime(appointment.getAppointmentTime());
            app.setAppointmentDate(appointment.getAppointmentDate());
            app.setPatient(patients);
            app.setStatus("Schedule");

            UUID uniqueId = UUID.randomUUID();
            app.setRoomId(uniqueId.toString());
            appointmentRepo.save(app);

            return true;
        }

        else {
            return false;
        }
    }

    public Appointment findAppointment(Long id) {
       Optional<Appointment> appointment= appointmentRepo.findById(id);
       if(appointment.isPresent()){
           return appointment.get();
       }
       else {
           throw new IllegalArgumentException("Invalid Id for appointment");
       }
    }

    public List<Appointment> findAppointmentForPatient(Long id){
        List<Appointment> appointmentList = appointmentRepo.findAppointmentByPatientId(id);
        return appointmentList;
    }

    public List<Appointment> findTodaysAppointmentOfDoctor(Long id)
    {
        LocalDate currentDate= LocalDate.now();
        List<Appointment> appointmentList=appointmentRepo.findTodayAppointmentsForDoctor(currentDate,id);
        return appointmentList;
    }

    public List<Appointment> findAppointmentSchedule(Long id){
        LocalDate currentDate = LocalDate.now();
        System.out.println("Todays Date is "+currentDate);
        String status = "Schedule";
        List<Appointment> appointmentList = appointmentRepo.
                findAllAppointmentByPatientIdAndStatusAndAppointmentDate(id,status,currentDate);

        System.out.println("List is "+appointmentList.isEmpty());
        System.out.println("Appointment is trying to fetch");
        return appointmentList;
    }

}
