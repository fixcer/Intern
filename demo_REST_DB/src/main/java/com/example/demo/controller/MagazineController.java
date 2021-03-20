package com.example.demo.controller;

import com.example.demo.model.Magazine;
import com.example.demo.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @GetMapping("/magazines")
    public List<Magazine> getAllMagazines() {
        return magazineService.findAll();
    }

    @PostMapping("/magazine")
    public Magazine createMagazine(@Valid @RequestBody Magazine magazine) {
        return magazineService.createMagazine(magazine);
    }

    @GetMapping("/magazine/{id}")
    public Magazine getMagazineById(@PathVariable(value = "id") Long id) {
        return magazineService.findById(id);
    }

    @PutMapping("/magazine/{id}")
    public Magazine updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Magazine magazineDetails) {
        Magazine magazine = magazineService.updateMagazine(id, magazineDetails);
        return magazine;
    }

    @DeleteMapping("/magazine/{id}")
    public ResponseEntity<?> deleteMagazine(@PathVariable(value = "id") Long id) {
        return magazineService.deleteMagazine(id);
    }
}
