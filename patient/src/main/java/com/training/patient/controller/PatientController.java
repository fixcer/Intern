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
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public Page<Patient> fetchAllPatients(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return patientService.fetchAll(pageNumber, pageSize);
    }

    @GetMapping("/size")
    public int getSizePatients() {
        return patientService.getSize();
    }

    @PostMapping("")
    public ResponseEntity<?> createPatient(@Valid @RequestBody Patient item) {
        item.setUpdated_at(new Date());
        item.setCreated_at(new Date());

        Patient patient = patientService.createPatient(item);
        return ResponseEntity.status(200).body(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchPatient(@PathVariable(value = "id") Long id) {
        Patient patient = patientService.findById(id);

        return ResponseEntity.status(200).body(patient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable(value = "id") Long id, @Valid @RequestBody Patient item) {
        Patient patient = patientService.updatePatient(id, item);

        if (patient.getId() != null) {
            return  ResponseEntity.status(200).body(patient);
        }

        return  ResponseEntity.status(400).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") Long id) {
        return patientService.deletePatient(id);
    }
}
