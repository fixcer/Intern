package com.training.patient.service;

import com.training.patient.model.Patient;
import com.training.patient.repository.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceTest {

    @MockBean
    PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        Mockito.when(patientRepository.findAll())
                .thenReturn(IntStream.range(0, 10)
                        .mapToObj(i -> new Patient((long) i, "name-" + i, "gender-" + i, i, "email-"+ i, "phone-" + i,new Date(), new Date()))
                        .collect(Collectors.toList()));
    }

    @Test
    public void getSize() {
        Assert.assertEquals(10, patientService.getSize());
    }

    @Test
    void findById() {
    }

    @Test
    void fetchAll() {
    }


    @Test
    void createPatient() {
    }

    @Test
    void updatePatient() {
    }

    @Test
    void deletePatient() {
    }
}