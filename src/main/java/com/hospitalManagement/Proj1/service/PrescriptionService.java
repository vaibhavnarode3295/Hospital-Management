package com.hospitalManagement.Proj1.service;

import com.hospitalManagement.Proj1.model.Prescription;
import com.hospitalManagement.Proj1.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepo prescriptionRepo;

    public void savePrescription(Prescription prescription)
    {
        prescription.getAppointment().setStatus("Treted");
        LocalDate localDate=LocalDate.now();
        prescription.setDateIssued(localDate);
        prescriptionRepo.save(prescription);
    }

    public List<Prescription> getList(Long id)
    {
        List<Prescription> list = prescriptionRepo.findByPatientId(id);
        return list;
    }

}
