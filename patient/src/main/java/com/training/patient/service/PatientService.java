package com.training.patient.service;

import com.training.patient.exception.ResourceNotFoundException;
import com.training.patient.model.Patient;
import com.training.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
    }

    public Page<Patient> fetchAll(int pageNumber, int pageSize) {
        Page<Patient> page = patientRepository.findAll(
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id")));
        return page;
    }

    public int getSize() {
        return patientRepository.findAll().size();
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));


        patient.setName(patientDetails.getName());
        patient.setGender(patientDetails.getGender());
        patient.setAge(patientDetails.getAge());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone_number(patientDetails.getPhone_number());
        patient.setUpdated_at(new Date());

        return patientRepository.save(patient);
    }

    public ResponseEntity<Object> deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        patientRepository.delete(patient);

        return ResponseEntity.ok().build();
    }
}
