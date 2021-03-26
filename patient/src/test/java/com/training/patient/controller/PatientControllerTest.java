package com.training.patient.controller;

import com.training.patient.exception.ResourceNotFoundException;
import com.training.patient.model.Patient;
import com.training.patient.service.PatientService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.is;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientService patientService;

    //The setUp() method is omitted.

    @Test
    public void fetchAllPatients() throws Exception {
        Page<Patient> patients = new PageImpl<Patient>(IntStream.range(0, 10)
                .mapToObj(i -> new Patient((long) i, "name-" + i, "gender-" + i, i, "email-"+ i, "phone-" + i,new Date(), new Date()))
                .collect(Collectors.toList()));


        when(patientService.fetchAll(0, 10)).thenReturn(patients);

        mvc.perform(get("/api/v1/patients?pageNumber=0&pageSize=10"))
                .andExpect(status().isOk());

        verify(patientService, times(1)).fetchAll(0, 10);
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void getSizePatients() throws Exception {
        Page<Patient> patients = new PageImpl<Patient>(IntStream.range(0, 10)
                .mapToObj(i -> new Patient((long) i, "name-" + i, "gender-" + i, i, "email-"+ i, "phone-" + i,new Date(), new Date()))
                .collect(Collectors.toList()));


        when(patientService.fetchAll(0, 10)).thenReturn(patients);

        mvc.perform(get("/api/v1/patients/size"))
                .andExpect(status().isOk());

        verify(patientService, times(1)).getSize();
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void fetchPatient_Not_Found() throws Exception {
        when(patientService.findById(1L)).thenThrow(new ResourceNotFoundException("Patient", "id", 1L));

        mvc.perform(get("/api/v1/patients/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(patientService, times(1)).findById(1L);
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void fetchPatient() throws Exception {
        Patient patient = new Patient(1L, "Toan", "Male", 22, "v.toanng@vinbrain.net", "0969191098", new Date(), new Date());
        when(patientService.findById(1L)).thenReturn(patient);

        mvc.perform(get("/api/v1/patients/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Toan")));

        verify(patientService, times(1)).findById(1L);
        verifyNoMoreInteractions(patientService);
    }
}