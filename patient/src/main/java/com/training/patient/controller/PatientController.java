package com.training.patient.controller;

import com.training.patient.model.Patient;
import com.training.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public Page<Patient> fetchAllPatients(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return patientService.fetchAll(pageNumber, pageSize);
    }

    @GetMapping("/patients/size")
    public int getSizePatients() {
        return patientService.getSize();
    }

    @PostMapping("/patient")
    public ResponseEntity<?> createPatient(@Valid @RequestBody Patient item) {
        item.setUpdated_at(new Date());
        item.setCreated_at(new Date());

        Patient patient = patientService.createPatient(item);
        return ResponseEntity.status(200).body(patient);
    }

    @GetMapping("/patient/{id}")
    public Patient fetchPatient(@PathVariable(value = "id") Long id) {
        return patientService.findById(id);
    }

    @PatchMapping("/patient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable(value = "id") Long id, @Valid @RequestBody Patient patientDetails) {
        Patient currentPatient = fetchPatient(id);

        if (currentPatient.getAge() == patientDetails.getAge() && currentPatient.getEmail().equals(patientDetails.getEmail()) && currentPatient.getName().equals(patientDetails.getName()) && currentPatient.getGender().equals(patientDetails.getGender()) && currentPatient.getPhone_number().equals(patientDetails.getPhone_number())) {
            return ResponseEntity.badRequest().build();
        }
        Patient patient = patientService.updatePatient(id, patientDetails);

        return  ResponseEntity.status(200).body(patient);
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Long id) {
        return patientService.deletePatient(id);
    }
}
